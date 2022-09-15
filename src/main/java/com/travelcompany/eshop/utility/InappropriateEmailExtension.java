package com.travelcompany.eshop.utility;

/**
 *
 * @author Grproth
 */
public class InappropriateEmailExtension extends Exception {
    
    public InappropriateEmailExtension( String errorMessage) {
        
        super(errorMessage);
    }
}
