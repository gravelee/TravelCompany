package com.travelcompany.eshop.utility;

/**
 *  This class is a custom exception of our application.
 *  This is called when the email is has inappropriate value.
 * 
 *  @author Grproth
 */
public class InappropriateEmailExtension extends Exception {
    
    public InappropriateEmailExtension( String errorMessage) {
        
        super(errorMessage);
    }
}
