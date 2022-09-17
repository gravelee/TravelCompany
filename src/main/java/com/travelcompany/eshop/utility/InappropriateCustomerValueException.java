package com.travelcompany.eshop.utility;

/**
 *  This class is a custom exception of our application.
 *  This is called when the customer is null or one of the customers attributes
 *  has inappropriate value.
 * 
 *  @author Grproth
 */
public class InappropriateCustomerValueException extends Exception {
    
    public InappropriateCustomerValueException( final String errorMessage) {
        
        super(errorMessage);
    }
}
