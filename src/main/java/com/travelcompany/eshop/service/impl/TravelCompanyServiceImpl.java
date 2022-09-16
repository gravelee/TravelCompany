package com.travelcompany.eshop.service.impl;

import com.travelcompany.eshop.domain.CategoryType;
import com.travelcompany.eshop.domain.Customer;
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
import com.travelcompany.eshop.utility.InappropriateCustomerValueException;
import com.travelcompany.eshop.utility.InappropriateItineraryValueException;
import com.travelcompany.eshop.utility.InappropriateOrderValueException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javatuples.Pair;

/**
 *  This is an implementation of the TravelCompanyService interface.
 * 
 *  @author Grproth
 */
public class TravelCompanyServiceImpl implements TravelCompanyService{
    
    //  The file names within the project file of the .csv data files to be red.
    
    private static final String CUSTOMERS_FILE_NAME = "customers.csv";
    private static final String ITINERARIES_FILE_NAME = "itineraries.csv";
    private static final String ORDERS_FILE_NAME = "orders.csv";
    
    //  The different data lists of our program ( Customer, Itinerary, Order).
    
    private final CustomersRepository customersRepository 
            = new CustomersRepositoryImpl();
    
    private final ItinerariesRepository itinerariesRepository 
            = new ItinerariesRepositoryImpl();
    
    private final OrdersRepository ordersRepository 
            = new OrdersRepositoryImpl();
    
    
    /**
     *  It reads the appropriate data and implements the business logic about 
     *  the discounts.
     *  
     *  @param  categoryType    ( the category type of the customer)
     *  @param  paymentType     ( the payment type of the order)
     *  @param  price           ( the original price of the itinerary)
     *  @return BigDecimal      ( the processed price to be payed by the customer)
     */
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
    
    
    /**
     *  We load the data of a .csv file and then we write them into the customer list.
     *  We also check if the customer to be added is valid based of business logic.
     */
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

            try{
            
                if( GeneralUtility.isValidCustomer(customer))
                    customersRepository.addCustomer(customer);
            }
            catch( InappropriateCustomerValueException ex){
                
                System.out.println("\n");
                Logger.getLogger( TravelCompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    /**
     *  We load the data of a .csv file and then we write them into the itinerary list.
     *  We also check if the itinerary to be added is valid based of business logic.
     */
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

            try{
            
                if( GeneralUtility.isValidItinerary(itinerary))
                    itinerariesRepository.addItinerary(itinerary);
            }
            catch( InappropriateItineraryValueException ex){
                
                System.out.println("\n");
                Logger.getLogger( TravelCompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    /**
     *  We load the data of a .csv file and then we write them into the order list.
     *  We also check if the order to be added is valid based of business logic.
     */
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

            if( !GeneralUtility.isValidId( customersRepository.readCustomers(), customerId))
                continue;
            
            if( !GeneralUtility.isValidId( itinerariesRepository.readItineraries(), itineraryId))
                continue;
            
            Order order = new Order(oId,customerId,itineraryId,
                    paymentType,paymentAmount);
            
            try{
            
                if( GeneralUtility.isValidOrder(order))
                    ordersRepository.addOrder(order);
            }
            catch( InappropriateOrderValueException ex){
                
                System.out.println("\n");
                Logger.getLogger( TravelCompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    /**
     *  We save the data of a customer into a .csv file.
     *  The data does not need validity checking.
     */
    @Override
    public void saveCustomersData(){
        
        GeneralUtility.writeCsvFile( 
            customersRepository.readCustomers(), CUSTOMERS_FILE_NAME);
    }
    
    
    /**
     *  We save the data of an itinerary into a .csv file.
     *  The data does not need validity checking.
     */
    @Override
    public void saveItinerariesData(){
        
        GeneralUtility.writeCsvFile( 
            itinerariesRepository.readItineraries(), ITINERARIES_FILE_NAME);
    }
    
    
    /**
     *  We save the data of an order into a .csv file.
     *  The data does not need validity checking.
     */
    @Override
    public void saveOrdersData(){
        
        GeneralUtility.writeCsvFile( 
            ordersRepository.readOrders(), ORDERS_FILE_NAME);
    }
    
    
    /**
     *  We create a new order to be added into our order list and we also check
     *  the validity of the order and if the ids of the customer and the 
     *  itinerary exists already.
     * 
     *  @param  customerId  ( the customer id to be checked)
     *  @param  itineraryId ( the itinerary id to be checked)
     *  @param  paymentType ( the payment type of the order)
     *  @return             ( true if the order has been created and added 
     *                          to the list, false otherwise)
     */
    @Override
    public boolean createNewOrder( long customerId, 
            long itineraryId, PaymentType paymentType){
        
        Customer customer = customersRepository.readCustomer(customerId);
        Itinerary itinerary = itinerariesRepository.readItinerary(itineraryId);
        
        try {

            GeneralUtility.isValidCustomer(customer);

        } catch ( InappropriateCustomerValueException ex) {
            
            System.out.println("\n");
            Logger.getLogger( TravelCompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        try {
            
            GeneralUtility.isValidItinerary(itinerary);
            
        } catch ( InappropriateItineraryValueException ex) {
            
            System.out.println("\n");
            Logger.getLogger( TravelCompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        long newId; 
        
        // could put Long.MAX_VALUE for the second arg but for the sake of
        // our application I will put a smaller number (100) to look better.
        newId = GeneralUtility.createNewId( 
                ordersRepository.readOrders(), 100); 
        
        
        BigDecimal discountedPrice = discount( customer.getCategory(), 
            paymentType, itinerary.getPrice());
        
        Order newOrder = new Order( newId, customer.getId(), itinerary.getId(),
            paymentType, discountedPrice);
        
        try {
            
            if( GeneralUtility.isValidOrder(newOrder)){
                
                ordersRepository.addOrder(newOrder);
                return true;
            }
            
        } catch (InappropriateOrderValueException ex) {
            
            System.out.println("\n");
            Logger.getLogger( TravelCompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
    /**
     *  Displays all the orders from the list.
     */
    @Override
    public void displayAllOrdersWithCosts(){
        
        System.out.println(Order.header());
        
        for( Order order : ordersRepository.readOrders())
            System.out.println(order.toString());
    }
    
    
    /**
     *  Displays all itineraries that pass the filter.
     *  Specifically if the departure or destination strings are null they 
     *  do not participate to the filtering algorithm. If one of them is not
     *  null it participates and if both of them are not null they participate
     *  both.
     * 
     *  The algorithm filters all the data based on the first string ( if not null)
     *  and then filters the remaining data for the second string ( if not null).
     * 
     *  Basically we check if the itineraries have the specific departure and 
     *  adds the itineraries to the filter list itineraries. Then we check if 
     *  the itineraries have the specific destination and then prints them to
     *  the user.
     * 
     *  @param departure     ( the first string to be checked)
     *  @param destination   ( the second string to be checked)
     */
    @Override
    public void displaySpecificItineraries( String departure, String destination){  // either of these or both can be null.
        
        System.out.println(Itinerary.header());
        
        List<Itinerary> itineraries = new ArrayList<>();
        
        if( departure != null){
        
            for( Itinerary itinerary : itinerariesRepository.readItineraries()){

                if( itinerary.getDepAC().equals(departure))
                    itineraries.add(itinerary);
            }
            
            if( destination != null){
                
                for( Itinerary itinerary : itineraries){

                    if( itinerary.getDesAC().equals(destination))
                        System.out.println(itinerary.toString());
                }
            }
            else{
             
                for( Itinerary itinerary : itineraries)
                    System.out.println(itinerary.toString());
            }
        }
        else if( destination != null){
            
            for( Itinerary itinerary : itinerariesRepository.readItineraries()){

                if( itinerary.getDesAC().equals(destination))
                    System.out.println(itinerary.toString());
            }
        }
        else{   // prints everything cause we did not add restrictions.
            
            for( Itinerary itinerary : itinerariesRepository.readItineraries())
                System.out.println(itinerary.toString());
        }
    }
    
    
    /**
     *  Displays all customers that pass the filter.
     *  Specifically if the fromTicketNumber number is zero or fromCost big 
     *  decimal is zero or null they do not participate to the filtering algorithm. 
     *  If one of them is not zero or null it participates and if both of them are 
     *  not zeros or null they participate both.
     * 
     *  The algorithm filters all the data based on the integer ( if not zero)
     *  and then filters the remaining data based on the big decimal 
     *  ( if not zero or null).
     * 
     *  We also use an internal data structure of a map to do the job.
     *  We also use lambda expressions to do the job.
     * 
     *  Basically we check if the customer has number of tickets equals or more
     *  than the fromTicketNumber int and we also check if the customer has equals 
     *  or more than the fromCost number of the sum of the costs of the specific 
     *  customers orders. In the end we prints the appropriate data.
     * 
     *  @param fromTicketNumber 
     *  @param fromCost         
     */
    @Override
    public void displayCustomers( int fromTicketNumber, BigDecimal fromCost){   // the numbers can be 0 (0.0) or null for BigDecimal.
        
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
        
//        System.out.println( "\n" + ordersPerCustomerIdWithTotalAmount.size() 
//              + "\n" + ordersPerCustomerIdWithTotalAmount.toString() + "\n");
        
        if( fromTicketNumber != 0){
        
            if( fromCost != null && fromCost.compareTo( BigDecimal.ZERO) == 1){
                
                ordersPerCustomerIdWithTotalAmount.forEach( ( key, pair) -> { 
                
                if( ( pair.getValue0() >= fromTicketNumber) && 
                        ( pair.getValue1().compareTo(fromCost) == 1
                            || pair.getValue1().compareTo(fromCost) == 0)){
                    
                    Customer customer = customersRepository.readCustomer(key);
                    
                    if( customer != null)
                        System.out.println(customer.toString());
                }});
            }
            else{
             
                ordersPerCustomerIdWithTotalAmount.forEach( ( key, pair) -> { 
                
                if( pair.getValue0() >= fromTicketNumber){
                    
                    Customer customer = customersRepository.readCustomer(key);
                    
                    if( customer != null)
                        System.out.println(customer.toString());
                }});
            }
        }
        else if( fromCost != null && fromCost.compareTo( BigDecimal.ZERO) == 1){
            
            ordersPerCustomerIdWithTotalAmount.forEach( ( key, pair) -> { 
                
            if( ( pair.getValue1().compareTo(fromCost) == 1
                || pair.getValue1().compareTo(fromCost) == 0)){

                Customer customer = customersRepository.readCustomer(key);
                    
                if( customer != null)
                    System.out.println(customer.toString());
            }});
        }
        else{
            
            for( Customer customer : customersRepository.readCustomers())
                System.out.println(customer.toString());
        }
    }
    
    
    /**
     *  We check if the customers have done any orders and if not then we 
     *  print them.
     */
    @Override
    public void displayAllCustomersWithoutOrders(){
        
        System.out.println(Customer.header());
        
        for( Customer customer : customersRepository.readCustomers()){
            
            boolean hasOrdered = false;
            
            for( Order order : ordersRepository.readOrders()){
                
                if( customer.getId() == order.getCustomerId())
                    hasOrdered = true;
            }
            
            if( !hasOrdered)
                System.out.println(customer.toString());
        }
    }
}
