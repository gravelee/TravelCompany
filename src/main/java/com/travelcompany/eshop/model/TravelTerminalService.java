
package com.travelcompany.eshop.model;

import com.travelcompany.eshop.util.HelperMethods;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Here we implement the business logic (buying tickets & giving discounts etc).
 * 
 * All the copies of arrayLists done in every call of HelperMethod methods can
 * be narrowed to zero. To do that we can implement all the code into the
 * TravelTerminalService class. But then it will be not so readable. 
 * 
 * I made a choice here to do the implementation into another helper class with 
 * static methods/attributes to make this class more readable and understandable. 
 * Although in future versions this may change.
 * 
 * @author Grproth
 */
public class TravelTerminalService {
    
    private ArrayList<Customer> customers;
    private ArrayList<Itinerary> itineraries;
    private ArrayList<Order> orders;
    
    public TravelTerminalService(){
        
        customers = (ArrayList<Customer>) HelperMethods
                .readFile( HelperMethods.CUSTOMER_FILE_NAME);
        itineraries = (ArrayList<Itinerary>) HelperMethods
                .readFile( HelperMethods.ITINERARIES_FILE_NAME);
        orders = (ArrayList<Order>) HelperMethods
                .readFile( HelperMethods.ORDERS_FILE_NAME);
    }
    
    public void orderATicket( Customer customer, Itinerary itinerary, 
            PaymentType paymentType){
        
        Random rand = new Random(); // this is not so great.
        
        long oId = rand.nextLong(0,1_000);  // can change to higher number
        long cId = customer.getId();
        long iId = itinerary.getId();
        
        BigDecimal processedPrice = new BigDecimal( 
            HelperMethods.discount( customer.getCategory(), paymentType, 
                    itinerary.getPrice()));
        
        orders.add( new Order( oId,cId,iId,paymentType,processedPrice));
    }
    
    public void saveAllDataToFiles(){
        
        HelperMethods.writeFile( customers, HelperMethods.CUSTOMER_FILE_NAME);
        HelperMethods.writeFile( itineraries, HelperMethods.ITINERARIES_FILE_NAME);
        HelperMethods.writeFile( orders, HelperMethods.ORDERS_FILE_NAME);
    }
}
