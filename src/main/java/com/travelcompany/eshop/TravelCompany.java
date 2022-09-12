
package com.travelcompany.eshop;


import com.travelcompany.eshop.model.CategoryType;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Order;
import com.travelcompany.eshop.model.PaymentType;
import com.travelcompany.eshop.model.TravelTerminalService;
import com.travelcompany.eshop.util.HelperMethods;
import java.math.BigDecimal;
import java.time.Instant;

/*
    Business analyst : 
        
        Reading the console input & writting to the console output ( No GUI or user interfaces).
        
        Read the database schemas (data) from .csv files to Java Collection (data structures).

        Implement the core classes/enums to model the whole system.
 */

 /*
    pakages: com.travelcompany.eshop   /.model  /.util
    
    1) eshop (domain)   
        Here we are implementing the driver(s) of the system.

    2) model            
        Here we model all the apropriate data types (classes, enums etc.)

    3) util             
        Here we put the helper tools (methods/attributes) that are global to the system.


    files:

    1) TravelCompany.java

    2) CategotyType.java  PaymentType.java  Customer.java  
        Itinerary.java  Order.java  TravelTerminalService.java

    3) HelperMethods.java
 */

 /*
    Model

    Class Customers --> individual / business
    
    
    
    Class TravelTerminalService
    
    (helperClass)
    Method buyTicket --> cash / credit card

    Method discount --> 
        business customer -10%,  individuals +20%,  pays with credit card -10%.

    Exception Handling when -->
        <user>@travelcompany.com,  
        issue a ticket and the itinerary does not exist,  
        creating itinerary and the airport code does not exist.

    List of the total number/cost of tickets.
    List of total offered itineraries per destination/departure.
    List of the customers with the most tickets/purchased sum.
    List of the customers with no purchased ticket yet.

    Make a Menu that can display the data above.

    Addons
    
    Add the functionality to add new purchases with timestamps to the file system.
    Add max number of sits to the itineraries.
    Write the collections to csv files.

    make the order id has some composure and not be random.
        
    Create a README.txt file to explain the process to run the program.
    
 */
/**
 * 
 * A ticket purchasing & reporting application.
 *
 * @author Grproth
 */
public class TravelCompany {

    public static void main(String[] args) {
        
        
        Customer customer = new Customer(333L, "Aris Gerax",
                "grave2lee@gmail.com","M. Andronikou 27A","Greek",
                CategoryType.INDIVIDUAL);
        
        Itinerary itinerary1 = new Itinerary(747L,"SKG","AMS","03/07/2022",
                "11.45", "Olympic Air Lines", new BigDecimal("267.56"));
        
        Itinerary itinerary2 = new Itinerary(748L,"SKG","ATH","15/12/22",
                "17:35", "Olympic Air Lines", new BigDecimal("67.56"));
            
        Order order = new Order(100456L,333L,747L,PaymentType.CASH,
                new BigDecimal("267.56"));
        
        
        TravelTerminalService service = new TravelTerminalService();
        
        service.orderATicket(customer, itinerary2, PaymentType.CREDIT_CARD); 
        // makes an order.
        
        service.saveAllDataToFiles();   // this is called to save the new order.
        
    }
}
