package com.travelcompany.eshop.utility;

/**
 *
 * @author Grproth
 */
public class InappropriateOrderPriceException extends Exception{
    
    public InappropriateOrderPriceException( String errorMessage) {
        
        super(errorMessage);
    }
}
