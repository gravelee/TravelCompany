package com.travelcompany.eshop.service.impl;

import com.travelcompany.eshop.domain.CategoryType;
import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.IdParser;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.Order;
import com.travelcompany.eshop.domain.PaymentType;
import com.travelcompany.eshop.repository.CustomersRepository;
import com.travelcompany.eshop.repository.ItinerariesRepository;
import com.travelcompany.eshop.repository.OrdersRepository;
import com.travelcompany.eshop.repository.impl.CustomersRepositoryImpl;
import com.travelcompany.eshop.repository.impl.ItinerariesRepositoryImpl;
import com.travelcompany.eshop.repository.impl.OrdersRepositoryImpl;
import com.travelcompany.eshop.service.TravelCompanyService;
import com.travelcompany.eshop.utility.GeneralUtility;
import com.travelcompany.eshop.utility.InappropriateAirportCodeException;
import com.travelcompany.eshop.utility.InappropriateCustomerValueException;
import com.travelcompany.eshop.utility.InappropriateEmailExtension;
import com.travelcompany.eshop.utility.InappropriateItineraryValueException;
import com.travelcompany.eshop.utility.InappropriateOrderPriceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javatuples.Pair;

/**
 *
 * @author Grproth
 */
public class TravelCompanyServiceImpl implements TravelCompanyService{
    
    private static final String CUSTOMERS_FILE_NAME = "customers.csv";
    private static final String ITINERARIES_FILE_NAME = "itineraries.csv";
    private static final String ORDERS_FILE_NAME = "orders.csv";
    
    private final CustomersRepository customersRepository 
            = new CustomersRepositoryImpl();
    
    private final ItinerariesRepository itinerariesRepository 
            = new ItinerariesRepositoryImpl();
    
    private final OrdersRepository ordersRepository 
            = new OrdersRepositoryImpl();
    
    
    private BigDecimal discount( final CategoryType categoryType, 
        final PaymentType paymentType, final BigDecimal price){
        
        double currentDiscount = 0.0;
        
        if( categoryType == CategoryType.INDIVIDUAL)
            currentDiscount -= 0.2;
        else    // categoryType == CategoryType.BUSINESS
            currentDiscount += 0.1;
        
        if( paymentType == PaymentType.CREDIT_CARD)
            currentDiscount += 0.1;
        
        double processedPrice = price.doubleValue() 
                - price.doubleValue()*currentDiscount;
        
        return new BigDecimal(processedPrice);
    }
    
    
    @Override
    public void loadCustomersData(){
        
        List<String[]> list = GeneralUtility.readCsvFile(CUSTOMERS_FILE_NAME);
        
        long cId;
        String name, email, address;
        String[] nationality;
        CategoryType categoryType;

        for( String[] temp: list){

            cId = Long.parseLong(temp[0]);
            name = temp[1];
            email = temp[2];
            address = temp[3];
            nationality = temp[4].split(" ");
            categoryType = 
                ("Business".equals(temp[5]))
                    ?CategoryType.BUSINESS
                    :CategoryType.INDIVIDUAL;

            Customer customer = new Customer(cId,name,email,
                    address,nationality,categoryType);

            customersRepository.addCustomer(customer);
        }
    }
    
    
    @Override
    public void loadItinerariesData(){
        
        List<String[]> list = GeneralUtility.readCsvFile(ITINERARIES_FILE_NAME);
        
        long iId;
        String depAC, desAC, airline, depDate, depTime;
        BigDecimal price;

        for( String[] temp: list){

            iId = Long.parseLong(temp[0]);
            depAC = temp[1];
            desAC = temp[2];
            depDate = temp[3];
            depTime = temp[4];
            airline = temp[5];
            price = new BigDecimal(temp[6]);

            Itinerary itinerary = new Itinerary(iId,depAC,desAC,
                    depDate,depTime,airline,price);

            itinerariesRepository.addItinerary(itinerary);
        }
    }
    
    
    @Override
    public void loadOrdersData(){
        
        List<String[]> list = GeneralUtility.readCsvFile(ORDERS_FILE_NAME);
        
        long oId, customerId, itineraryId;
        PaymentType paymentType;
        BigDecimal paymentAmount;

        for( String[] temp: list){

            oId = Long.parseLong(temp[0]);
            customerId = Long.parseLong(temp[1]);
            itineraryId = Long.parseLong(temp[2]);
            paymentType = 
                    ("Cash".equals(temp[3]))
                    ?PaymentType.CASH
                    :PaymentType.CREDIT_CARD;
            paymentAmount = new BigDecimal(temp[4]);

            Order order = new Order(oId,customerId,itineraryId,
                    paymentType,paymentAmount);

            ordersRepository.addOrder(order);
        }
    }
    
    
    @Override
    public void saveCustomersData(){
        
        GeneralUtility.writeCsvFile( 
            customersRepository.readCustomers(), CUSTOMERS_FILE_NAME);
    }
    
    
    @Override
    public void saveItinerariesData(){
        
        GeneralUtility.writeCsvFile( 
            itinerariesRepository.readItineraries(), ITINERARIES_FILE_NAME);
    }
    
    
    @Override
    public void saveOrdersData(){
        
        GeneralUtility.writeCsvFile( 
            ordersRepository.readOrders(), ORDERS_FILE_NAME);
    }
    
    
    @Override
    public boolean createNewOrder( long customerId, 
            long itineraryId, PaymentType paymentType){
        
        Customer customer = customersRepository.readCustomer(customerId);
        Itinerary itinerary = itinerariesRepository.readItinerary(itineraryId);
        
        try {

            GeneralUtility.isValidCustomer(customer);

        } catch (InappropriateEmailExtension | InappropriateCustomerValueException ex) {

            Logger.getLogger(TravelCompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        try {
            
            GeneralUtility.isValidItinerary(itinerary);
            
        } catch (InappropriateAirportCodeException | InappropriateItineraryValueException ex) {
            
            Logger.getLogger(TravelCompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        long newId = GeneralUtility.createNewId( 
                ordersRepository.readOrders(), Long.MAX_VALUE);
        
        BigDecimal discountedPrice = discount( customer.getCategory(), 
            paymentType, itinerary.getPrice());
        
        Order newOrder = new Order( newId, customer.getId(), itinerary.getId(),
            paymentType, discountedPrice);
        
        try {
            
            if( GeneralUtility.isValidOrder(newOrder)){
                
                ordersRepository.addOrder(newOrder);
                return true;
            }
            
        } catch (InappropriateOrderPriceException ex) {
            
            Logger.getLogger(TravelCompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
    @Override
    public void displayAllOrdersWithCosts(){
        
        Order.header();
        
        for( Order order : ordersRepository.readOrders())
            order.toString();
    }
    
    
    @Override
    public void displaySpecificItineraries( String departure, String destination){  // either of these or both can be null.
        
        Itinerary.header();
        
        List<Itinerary> itineraries = new ArrayList<>();
        
        if( departure != null){
        
            for( Itinerary itinerary : itinerariesRepository.readItineraries()){

                if( itinerary.getDepAC().equals(departure))
                    itineraries.add(itinerary);
            }
            
            if( destination != null){
                
                for( Itinerary itinerary : itineraries){

                    if( itinerary.getDesAC().equals(destination))
                        itinerary.toString();
                }
            }
            else{
             
                for( Itinerary itinerary : itineraries)
                    itinerary.toString();
            }
        }
        else if( destination != null){
            
            for( Itinerary itinerary : itinerariesRepository.readItineraries()){

                if( itinerary.getDesAC().equals(destination))
                    itinerary.toString();
            }
        }
        else{   // prints everything cause we did not add restrictions.
            
            for( Itinerary itinerary : itinerariesRepository.readItineraries())
                itinerary.toString();
        }
    }
    
    
    @Override
    public void displayCustomers( int fromTicketNumber, BigDecimal fromCost){   // the numbers can be 0 (0.0) or null for BigDecimal.
        
        Customer.header();
        
        Map<Long,Pair<Integer,BigDecimal>> ordersPerCustomerIdWithTotalAmount = new HashMap<>();
        
        for( Order order : ordersRepository.readOrders()){

            long customerId = order.getCustomerId();

            if( !ordersPerCustomerIdWithTotalAmount.containsKey( customerId)){

                ordersPerCustomerIdWithTotalAmount.put( 
                        customerId, new Pair(1, order.getPaymentAmount()));
            }
            else{

                Pair<Integer,BigDecimal> pair = new Pair(
                    ordersPerCustomerIdWithTotalAmount.get(customerId).getValue0() + 1,
                    ordersPerCustomerIdWithTotalAmount.get(
                        customerId).getValue1().add(order.getPaymentAmount()));

                ordersPerCustomerIdWithTotalAmount.remove(customerId);
                ordersPerCustomerIdWithTotalAmount.put(customerId, pair);
            }
        }
        
        if( fromTicketNumber != 0){
        
            if( fromCost != null && fromCost.compareTo( BigDecimal.ZERO) == 1){
                
                ordersPerCustomerIdWithTotalAmount.forEach( ( key, pair) -> { 
                
                if( ( pair.getValue0() >= fromTicketNumber) && 
                        ( pair.getValue1().compareTo(fromCost) == 1
                            || pair.getValue1().compareTo(fromCost) == 0)){
                    
                    Customer customer = customersRepository.readCustomer(key);
                    
                    if( customer != null)
                        customer.toString();
                }});
            }
            else{
             
                ordersPerCustomerIdWithTotalAmount.forEach( ( key, pair) -> { 
                
                if( pair.getValue0() >= fromTicketNumber){
                    
                    Customer customer = customersRepository.readCustomer(key);
                    
                    if( customer != null)
                        customer.toString();
                }});
            }
        }
        else if( fromCost != null && fromCost.compareTo( BigDecimal.ZERO) == 1){
            
            ordersPerCustomerIdWithTotalAmount.forEach( ( key, pair) -> { 
                
            if( ( pair.getValue1().compareTo(fromCost) == 1
                || pair.getValue1().compareTo(fromCost) == 0)){

                Customer customer = customersRepository.readCustomer(key);
                    
                if( customer != null)
                    customer.toString();
            }});
        }
    }
    
    
    @Override
    public void displayAllCustomersWithoutOrders(){
        
        Customer.header();
        
        for( Customer customer : customersRepository.readCustomers()){
            
            boolean hasOrdered = false;
            
            for( Order order : ordersRepository.readOrders()){
                
                if( customer.getId() == order.getCustomerId())
                    hasOrdered = true;
            }
            
            if( !hasOrdered)
                customer.toString();
        }
    }
}
