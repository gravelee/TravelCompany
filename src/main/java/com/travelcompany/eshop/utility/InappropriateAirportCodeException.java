package com.travelcompany.eshop.utility;

/**
 *
 * @author Grproth
 */
public class InappropriateAirportCodeException extends Exception {
    
    public InappropriateAirportCodeException( String errorMessage) {
        
        super(errorMessage);
    }
}
