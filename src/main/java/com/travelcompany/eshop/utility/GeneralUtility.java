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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Grproth
 */
public class GeneralUtility {
    
    
    /**
     *  Takes a BigDecimal number and morphs it nicely to a String.
     * 
     * @param   bigDecimal  ( the number to be morphed)
     * @return  String      ( returns a morphed bigDecimal as a String)
     */
    public static String formatBigDecimal( BigDecimal bigDecimal){
        
        DecimalFormat df = new DecimalFormat();
        
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        
        return df.format(bigDecimal);
    }
    
    
    /**
     * Checks if the email parsed is valid.
     * 
     * @param   email   ( the email in question)
     * @return  Boolean ( if the email is valid it return true, otherwise false)
     * @throws  com.travelcompany.eshop.utility.InappropriateEmailExtension
     */
    public static boolean isValidEmail( String email) 
            throws InappropriateEmailExtension{
        
        if( email.contains("@travelcompany.com")) 
            throw new InappropriateEmailExtension(
                    "Error, the customers email extension is forbidden.");
        
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    
    /**
     * Checks if the customer parsed is valid.
     * 
     * @param   customer ( the customer in question)
     * @return  Boolean  ( if the customer is valid it return true, false otherwise)
     * @throws  com.travelcompany.eshop.utility.InappropriateEmailExtension
     */
    public static boolean isValidCustomer( Customer customer) 
            throws InappropriateEmailExtension, InappropriateCustomerValueException{
        
        if( customer == null) 
            throw new InappropriateCustomerValueException(
                    "Error, the customer value is null.");    
        
        return isValidEmail( customer.getEmail());
    }
    
    
    /**
     * Checks if the address parsed is valid.
     * 
     * @param   address ( the address in question)
     * @return  Boolean ( if the address is valid return true, false otherwise)
     */
    public static boolean isValidAddress( String address){
        
        String regex = "[A-Za-z0-9'\\.\\-\\s\\,]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
    
    
    /**
     * Checks if the itinerary parsed is valid.
     * 
     * @param   itinerary   ( the itinerary in question)
     * @return  Boolean     ( if the itinerary is valid it return true, false otherwise)
     * @throws  com.travelcompany.eshop.utility.InappropriateAirportCodeException
     * @throws  com.travelcompany.eshop.utility.InappropriateItineraryValueException
     */
    public static boolean isValidItinerary( Itinerary itinerary) 
            throws InappropriateAirportCodeException, InappropriateItineraryValueException{
        
        if( itinerary == null) 
            throw new InappropriateItineraryValueException(
                    "Error, the itinerary value is null.");  
        
        if( itinerary.getDepAC() == null)
            throw new InappropriateAirportCodeException(
                    "Error, the itinerary departure code is none existent.");
            
        if( itinerary.getDesAC() == null) 
            throw new InappropriateAirportCodeException(
                    "Error, the itinerary destination code is none existent.");
        
        return true;
    }
    
    
    /**
     * Checks if the order parsed is valid.
     * 
     * @param   order       ( the itinerary in question)
     * @return  Boolean     ( if the itinerary is valid it return true, false otherwise)
     * @throws  com.travelcompany.eshop.utility.InappropriateOrderPriceException
     */
    public static boolean isValidOrder( Order order) 
            throws InappropriateOrderPriceException{
        
        if( order == null) 
            return false;    
        
        if( order.getPaymentAmount().compareTo(BigDecimal.ZERO) == -1)
            throw new InappropriateOrderPriceException(
                    "Error, the orders payment amount is negative.");
            
        if( order.getPaymentAmount().compareTo(BigDecimal.ZERO) == 0)
            throw new InappropriateOrderPriceException(
                    "Error, the orders payment amount is zero.");
        
        return true;
    }
    
    
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
    
    
    public static long createNewId( final List<Order> list, final long maxValue){
        
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
}
