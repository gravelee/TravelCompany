
package com.travelcompany.eshop.util;

import com.travelcompany.eshop.model.CategoryType;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Order;
import com.travelcompany.eshop.model.PaymentType;
import com.travelcompany.eshop.model.TravelTerminalService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;

/**
 *
 * This helper class has method tools to use for implementing the business logic
 * or read/write all the appropriate data from/to .csv files in the project
 * directory.
 * 
 * @author Grproth
 */
public class HelperMethods {
    
    public static final String CUSTOMER_FILE_NAME = "customers.csv";
    public static final String ITINERARIES_FILE_NAME = "itineraries.csv";
    public static final String ORDERS_FILE_NAME = "orders.csv";
    //public static final String PATTERN_FORMAT = "dd/MM/yyyy"; 
    // if we change the depDate of Itinerary class to other than String.
    
    /*
        This method takes a BigDecimal number and morphs it nicely to a String.
    */
    public static String formatBigDecimal( BigDecimal bigDecimal){
        
        DecimalFormat df = new DecimalFormat();
        
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        
        return df.format(bigDecimal);
    }
    
    /*
        The business logic behind the discounts. It takes as input the 
        CategoryType, the PaymentType and the price. Returns the 
        processed price (discounted or not).
    */
    public static double discount( final CategoryType categoryType, 
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
        
        return processedPrice;
    }
    
    /*
        Here we read a specific .csv file and return the list 
        associated with it.
    */
    public static ArrayList<?> readFile( final String fileName){
        
        ArrayList<String[]> inputList = new ArrayList<>();
        BufferedReader br;
        
        try{
            
            br = new BufferedReader(new FileReader(fileName));
             
            String str;

            while( ( str = br.readLine()) != null){

                inputList.add( str.split(","));
            }
        
        }catch( IOException ex){
            
            System.out.println("Problem openning the file " + fileName + "!");
            return null;
        }
           
        switch(fileName){

            case "customers.csv":

                ArrayList customersList = new ArrayList<Customer>();

                long cId;
                String name, email, address, nationality;
                CategoryType categoryType;

                for( String[] temp: inputList){

                    cId = Long.parseLong(temp[0]);
                    name = temp[1];
                    email = temp[2];
                    address = temp[3];
                    nationality = temp[4];
                    categoryType = 
                        ("Business".equals(temp[5]))
                            ?CategoryType.BUSINESS
                            :CategoryType.INDIVIDUAL;

                    Customer customer = new Customer(cId,name,email,
                            address,nationality,categoryType);

                    customersList.add(customer);
                }

                return customersList;

            case "itineraries.csv":

                ArrayList itinerariesList = new ArrayList<Itinerary>();

                long iId;
                String depAC, desAC, airline, depDate, depTime;
                BigDecimal price;

                for( String[] temp: inputList){

                    iId = Long.parseLong(temp[0]);
                    depAC = temp[1];
                    desAC = temp[2];
                    depDate = temp[3];
                    depTime = temp[4];
                    airline = temp[5];
                    price = new BigDecimal(temp[6]);

                    Itinerary itinerary = new Itinerary(iId,depAC,desAC,
                            depDate,depTime,airline,price);

                    itinerariesList.add(itinerary);
                }

                return itinerariesList;

            case "orders.csv":

                //long id, long customerId, long itineraryId, 
                //PaymentType paymentType, BigDecimal paymentAmount

                ArrayList ordersList = new ArrayList<Order>();

                long oId, customerId, itineraryId;
                PaymentType paymentType;
                BigDecimal paymentAmount;

                for( String[] temp: inputList){

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

                    ordersList.add(order);
                }
                
                return ordersList;

            default:

                return null;
        }
    }
    
    /*
        Here we are writing the different Lists back to the files. 
    */
    public static boolean writeFile( final ArrayList<?> list, final String fileName){

        FileWriter fos;
        String output = "";
        
        switch ( fileName) {
            
            case CUSTOMER_FILE_NAME:
                
                ArrayList<Customer> customersList = (ArrayList<Customer>) list;
                
                try{
                    
                    fos = new FileWriter( new File("customers.csv"), false);
                }
                catch( FileNotFoundException ex){
                    
                    System.out.println("Problem finding the file " + fileName + "!");
                    return false;
                }
                catch( IOException ex){
                    
                    System.out.println("Problem  opening the stream!");
                    return false;
                }   
                
                for( Customer c: customersList){
                    
                    output += c.toString() + "\n";
                }  
                
                try{
                    
                    fos.write(output);
                    fos.close();
                }
                catch( IOException ex){
                    
                    System.out.println("Problem writing the file " + fileName + "!");
                    return false;
                }   
                
                break;
                
            case ITINERARIES_FILE_NAME:
                
                ArrayList<Itinerary> itinerariesList = (ArrayList<Itinerary>) list;
                
                try{
                    
                    fos = new FileWriter( new File("itineraries.csv"), false);
                }
                catch( FileNotFoundException ex){
                    
                    System.out.println("Problem finding the file " + fileName + "!");
                    return false;
                }
                catch( IOException ex){
                    
                    System.out.println("Problem  opening the stream!");
                    return false;
                }   
                
                for(Itinerary i: itinerariesList){
                    
                    output += i.toString() + "\n";
                }  
                
                try{
                    
                    fos.write(output);
                    fos.close();
                }
                catch( IOException ex){
                    
                    System.out.println("Problem writing the file " + fileName + "!");
                    return false;
                }   
                
                break;
                
            default:
                //  ORDERS_FILE_NAME
                
                ArrayList<Order> ordersList = (ArrayList<Order>) list;
                
                try{
                    
                    fos = new FileWriter( new File("orders.csv"), false);
                }
                catch( FileNotFoundException ex){
                    
                    System.out.println("Problem finding the file " + fileName + "!");
                    return false;
                }
                catch( IOException ex){
                    
                    System.out.println("Problem  opening the stream!");
                    return false;
                }   
                
                for( Order o: ordersList){
                    
                    output += o.toString() + "\n";
                }   
                
                try{
                    
                    fos.write(output);
                    fos.close();
                }
                catch( IOException ex){
                    
                    System.out.println("Problem writing the file " + fileName + "!");
                    return false;
                }   
                
                break;
        }
        
        return true;
    }
}
