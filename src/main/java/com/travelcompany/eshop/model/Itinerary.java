
package com.travelcompany.eshop.model;

import com.travelcompany.eshop.util.HelperMethods;
import java.math.BigDecimal;

/**
 *
 * A class that represent the different itineraries and their info.
 * 
 * @author Grproth
 */
public class Itinerary {
    
    private long id;
    private String depAC;
    private String desAC;
    private String depDate; // can change to one of the java date & time classes.
    private String depTime; // can change to one of the java date & time classes.
    private String airline;
    private BigDecimal price;

    public Itinerary(long id, String depAC, String desAC, String depDate, 
            String depTime, String airline, BigDecimal price) {
        
        this.id = id;
        this.depAC = depAC;
        this.desAC = desAC;
        this.depDate = depDate;
        this.depTime = depTime;
        this.airline = airline;
        this.price = price;
    }
    
    public long getId(){
        
        return id;
    }
    
    public BigDecimal getPrice(){
        
        return price;
    }

    @Override
    public String toString() {
        
        return "" + id + "," + depAC + "," + desAC + "," + depDate + "," 
                + depTime + "," + airline + "," 
                + HelperMethods.formatBigDecimal(price);
    }
}
