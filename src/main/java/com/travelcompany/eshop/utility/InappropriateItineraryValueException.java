package com.travelcompany.eshop.utility;

/**
 *  This class is a custom exception of our application.
 *  This is called when the itinerary is null or one of the Itinerary's attributes
 *  has inappropriate value.
 * 
 *  @author Grproth
 */
public class InappropriateItineraryValueException extends Exception {

    public InappropriateItineraryValueException( final String errorMessage) {
        
        super(errorMessage);
    }
}
