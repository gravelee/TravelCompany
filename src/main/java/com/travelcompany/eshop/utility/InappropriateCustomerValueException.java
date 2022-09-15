package com.travelcompany.eshop.utility;

/**
 *
 * @author Grproth
 */
public class InappropriateCustomerValueException extends Exception{
    
    public InappropriateCustomerValueException( String errorMessage) {
        
        super(errorMessage);
    }
}
