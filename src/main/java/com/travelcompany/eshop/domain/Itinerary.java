package com.travelcompany.eshop.domain;

import com.travelcompany.eshop.utility.GeneralUtility;
import java.math.BigDecimal;

/**
 *  A class that represent the different itineraries and their info.
 *  We do extend from IdParser in order to reduce the number of methods called
 *  within GeneralUtility class.
 * 
 *  @author Grproth
 */
public class Itinerary extends IdParser {
    
    private final long id;
    private final String depAC;
    private final String desAC;
    private String depDate; // can change to one of java date & time classes.
    private String depTime; // can change to one of java date & time classes.
    private String airline;
    private BigDecimal price;

    public Itinerary( final long id, final String depAC, final String desAC, 
            final String depDate, final String depTime, final String airline, 
            final BigDecimal price) {
        
        this.id = id;
        this.depAC = depAC;
        this.desAC = desAC;
        this.depDate = depDate;
        this.depTime = depTime;
        this.airline = airline;
        this.price = price;
    }

    public Itinerary( final long createNewId) {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
        We override cause we inherit the method from IdParser abstruct class.
    */
    @Override
    public final long getId() {
        
        return id;
    }

    public final String getDepAC() {
        
        return depAC;
    }
    
    public final String getDesAC() {
        
        return desAC;
    }
    
    public final String getDepDate() {
        
        return depDate;
    }
    
    public final String getDepTime() {
        return depTime;
    }
    
    public final String getAirline() {
        return airline;
    }
    
    public final BigDecimal getPrice() {
        return price;
    }

    public final void setDepDate( final String depDate) {
        
        this.depDate = depDate;
    }

    public final void setDepTime( final String depTime) {
        
        this.depTime = depTime;
    }

    public final void setAirline( final String airline) {
        
        this.airline = airline;
    }

    public final void setPrice( final BigDecimal price) {
        
        this.price = price;
    }
    
    public static final String header() {
        
        return "\n\nid,depAC,decAC,depDate,depTime,airline,price\n\n";
    }
    
    @Override
    public final String toString() {
        
        return "" + id + "," + depAC + "," + desAC + "," + depDate + "," 
                + depTime + "," + airline + "," 
                + GeneralUtility.formatBigDecimal(price);
    }
}
