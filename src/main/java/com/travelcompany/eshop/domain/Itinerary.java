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
public class Itinerary extends IdParser{
    
    private final long id;
    private final String depAC;
    private final String desAC;
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

    public Itinerary(long createNewId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /*
        We did the override cause we inherit that method from IdParser abstruct class.
    */
    @Override
    public long getId() {
        
        return id;
    }

    public String getDepAC() {
        
        return depAC;
    }
    
    public String getDesAC() {
        
        return desAC;
    }
    
    public String getDepDate() {
        
        return depDate;
    }
    
    public String getDepTime() {
        return depTime;
    }
    
    public String getAirline() {
        return airline;
    }
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setDepDate(String depDate) {
        
        this.depDate = depDate;
    }

    public void setDepTime(String depTime) {
        
        this.depTime = depTime;
    }

    public void setAirline(String airline) {
        
        this.airline = airline;
    }

    public void setPrice(BigDecimal price) {
        
        this.price = price;
    }
    
    public static String header(){
        
        return "\n\nid,depAC,decAC,depDate,depTime,airline,price\n\n";
    }
    
    @Override
    public String toString() {
        
        return "" + id + "," + depAC + "," + desAC + "," + depDate + "," 
                + depTime + "," + airline + "," 
                + GeneralUtility.formatBigDecimal(price);
    }
}
