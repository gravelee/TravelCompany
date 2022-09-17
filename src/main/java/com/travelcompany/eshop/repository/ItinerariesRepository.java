package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.domain.Itinerary;
import java.math.BigDecimal;
import java.util.List;

/**
 *  This is the ItinerariesRepository interface, here we have all methods that
 *  a Itineraries Repository implementation needs to implement in order to have
 *  the appropriate logic.
 * 
 *  @author Grproth
 */
public interface ItinerariesRepository {
    
    boolean addItinerary( final Itinerary itinerary);
    boolean deleteItinerary( final long itineraryId);
    List<Itinerary> readItineraries();
    Itinerary readItinerary( final long itineraryId);
    boolean updateItineraryDepDate( final long itineraryId, 
            final String newDepDate);
    boolean updateItineraryDepTime( final long itineraryId, 
            final String newDepTime);
    boolean updateItineraryAirline( final long itineraryId, 
            final String newAirline);
    boolean updateItineraryPrice( final long itineraryId, 
            final BigDecimal newPrice);
}
