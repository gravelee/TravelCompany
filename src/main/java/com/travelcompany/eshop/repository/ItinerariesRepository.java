package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.domain.Itinerary;
import java.math.BigDecimal;
import java.util.List;

/**
 *  This is the ItinerariesRepository interface, here we have all the methods that
 *  a Itineraries Repository implementation needs to implement in order to have
 *  the appropriate logic.
 * 
 *  @author Grproth
 */
public interface ItinerariesRepository {
    
    boolean addItinerary( Itinerary itinerary);
    boolean deleteItinerary( long itineraryId);
    List<Itinerary> readItineraries();
    Itinerary readItinerary( long itineraryId);
    boolean updateItineraryDepDate( long itineraryId, String newDepDate);
    boolean updateItineraryDepTime( long itineraryId, String newDepTime);
    boolean updateItineraryAirline( long itineraryId, String newAirline);
    boolean updateItineraryPrice( long itineraryId, BigDecimal newPrice);
   
}
