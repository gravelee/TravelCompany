package com.travelcompany.eshop.utility;

/**
 *  This class is a custom exception of our application.
 *  This is called when the new ids do not have enough space for their names.
 * 
 *  @author Grproth
 */
public class MaxIdNumberException extends Exception {
    
    public MaxIdNumberException( final String errorMessage) {
        
        super(errorMessage);
    }
}
