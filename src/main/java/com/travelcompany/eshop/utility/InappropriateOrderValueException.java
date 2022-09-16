package com.travelcompany.eshop.utility;

/**
 *  This class is a custom exception of our application.
 *  This is called when the order is null or one of the orders attributes
 *  has inappropriate value.
 * 
 * @author Grproth
 */
public class InappropriateOrderValueException extends Exception{
    
    public InappropriateOrderValueException( String errorMessage) {
        
        super(errorMessage);
    }
}
