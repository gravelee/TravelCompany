package com.travelcompany.eshop.utility;

import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.IdParser;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  This class is a helper class that implements helper methods for other classes.
 *  
 *  @author Grproth
 */
public class GeneralUtility {
    
    
    /**
     *  Takes a BigDecimal number and morphs it nicely to a String.
     * 
     *  @param   bigDecimal  ( the number to be morphed)
     *  @return  String      ( returns a morphed bigDecimal as a String)
     */
    public static String formatBigDecimal( BigDecimal bigDecimal){
        
        DecimalFormat df = new DecimalFormat();
        
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        
        return df.format(bigDecimal);
    }
    
    
    /**
     *  Checks if the email parsed is valid.
     * 
     *  @param   email   ( the email in question)
     *  @return  Boolean ( if the email is valid it return true, otherwise false)
     *  @throws  com.travelcompany.eshop.utility.InappropriateEmailExtension
     */
    public static boolean isValidEmail( String email) 
            throws InappropriateEmailExtension{
        
        if( email.contains("@travelcompany.com"))
            throw new InappropriateEmailExtension(
                "Error, the customers email extension (@travelcompany.com) is forbidden.");
        
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    
    /**
     *  Checks if the address parsed is valid.
     * 
     *  @param   address ( the address in question)
     *  @return  Boolean ( if the address is valid return true, false otherwise)
     */
    public static boolean isValidAddress( String address){
        
        String regex = "[\\w\\s]*\\d*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
    
    
    /**
     *  Checks if the customer parsed is valid.
     * 
     *  @param   customer ( the customer in question)
     *  @return  Boolean  ( if the customer is valid it return true, false otherwise)
     *  @throws  com.travelcompany.eshop.utility.InappropriateCustomerValueException
     */
    public static boolean isValidCustomer( Customer customer) 
            throws InappropriateCustomerValueException{
        
        if( customer == null)
            throw new InappropriateCustomerValueException(
                    "Error, the customer value is null.");
        
        if( !isValidAddress( customer.getAddress()))
            return false;
        
        try{
            
             return isValidEmail( customer.getEmail());
        }
        catch( InappropriateEmailExtension ex){
        
            System.out.println("\n");
            Logger.getLogger( GeneralUtility.class.getName()).log(Level.SEVERE, null, ex);
            throw new InappropriateCustomerValueException("Inappropriate Customers email address.");
        }
    }
    
    
    /**
     *  Checks if the itinerary parsed is valid.
     * 
     *  @param   itinerary   ( the itinerary in question)
     *  @return  Boolean     ( if the itinerary is valid it return true, false otherwise)
     *  @throws  com.travelcompany.eshop.utility.InappropriateItineraryValueException
     */
    public static boolean isValidItinerary( Itinerary itinerary) 
            throws InappropriateItineraryValueException{
        
        if( itinerary == null)
            throw new InappropriateItineraryValueException(
                    "Error, the itinerary value is null.");  
        
        if( itinerary.getDepAC() == null)
            throw new InappropriateItineraryValueException(
                    "Error, the itinerary departure code is none existent.");
            
        if( itinerary.getDesAC() == null) 
            throw new InappropriateItineraryValueException(
                    "Error, the itinerary destination code is none existent.");
        
        return true;
    }
    
    
    /**
     *  Checks if the order parsed is valid.
     * 
     *  @param   order       ( the itinerary in question)
     *  @return  Boolean     ( if the itinerary is valid it return true, false otherwise)
     *  @throws  com.travelcompany.eshop.utility.InappropriateOrderValueException
     */
    public static boolean isValidOrder( Order order) 
            throws InappropriateOrderValueException{
        
        if( order == null) 
            return false;    
        
        if( order.getPaymentAmount().compareTo(BigDecimal.ZERO) == -1)
            throw new InappropriateOrderValueException(
                    "Error, the orders payment amount is negative.");
            
        if( order.getPaymentAmount().compareTo(BigDecimal.ZERO) == 0)
            throw new InappropriateOrderValueException(
                    "Error, the orders payment amount is zero.");
        
        return true;
    }
    
    
    /**
     *  Reads   the .csv file into a list of String arrays.
     * 
     *  @param  fileName                ( the file name of the data red)          
     *  @return List of String arrays.  ( the data returned in a list)
     */
    public static List<String[]> readCsvFile( String fileName){
        
        List<String[]> list = new ArrayList<>();
        BufferedReader br;
        
        try{
            
            br = new BufferedReader(new FileReader(fileName));
             
            String str;

            while( ( str = br.readLine()) != null){

                list.add( str.split(","));
            }
        
        }catch( IOException ex){
            
            System.out.println("Problem openning the file " 
                    + fileName + "!");
            return null;
        }
        
        return list;
    }
    
    
    /**
     *  Writes a list of data ( customers,itinerary,order) to a specific file.
     * 
     *  @param list     ( the list to be written)
     *  @param fileName ( the file name where we write the data)
     */
    public static void writeCsvFile( final List<?> list, final String fileName){
        
        FileWriter fos = null;
        String output = "";
        
        try{

            fos = new FileWriter( new File(fileName), false);
        }
        catch( FileNotFoundException ex){

            System.out.println("Problem finding the file " 
                    + fileName + "!");
        }
        catch( IOException ex){

            System.out.println("Problem  opening the stream!");
        }
        
        for( var c: list){

            output += c.toString() + "\n";
        }  

        try{

            fos.write(output);
            fos.close();
        }
        catch( IOException ex){

            System.out.println("Problem writing to the file " + fileName + "!");
        }
    }
    
    
    /**
     *  It creates a unique new id between [0, maxValue].
     * 
     *  @param  list        ( the list of the items to add a new id)
     *  @param  maxValue    ( the max value to be included for the randomized algorithm)
     *  @return The long id ( the new id to be returned)
     */
    public static long createNewId( final List<? extends IdParser> list, final long maxValue){
        
        Random rand = new Random();
        
        int count;
        long newId = rand.nextLong(0,maxValue);
        
        if( list == null)
                return newId;
        
        do{
            
            count = 0;
            
            for( var listValue : list){
                
                if( listValue.getId() != newId)
                    count++;
            }
            
            if( count == list.size())
                break;
            
            newId = rand.nextLong(0,maxValue);
            
        }while( true);
        
        return newId;
    }
    
    /**
     *  Takes as input a list and checks if the id exists within the list.
     * 
     * @param list  ( the list to be checked)
     * @param id    ( the id to be checked)
     * @return      ( true if the id exists, false otherwise)
     */
    public static boolean isValidId( List<? extends IdParser> list, long id){
        
        for( var item : list){
            
            if( item.getId() == id)
                return true;
        }
        
        return false;
    }
}
