package com.travelcompany.eshop.repository.impl;

import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.repository.ItinerariesRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  This is an implementation of the ItinerariesRepository interface.
 * 
 *  @author Grproth
 */
public class ItinerariesRepositoryImpl implements ItinerariesRepository{
    
    private final List<Itinerary> itineraries = new ArrayList<>();
    
    
    /**
     *  Tries to add a new itinerary to the list.
     * 
     *  @param  newItinerary    ( the itinerary to be added)
     *  @return Boolean         ( true if the itinerary has been added, false otherwise)
     */
    @Override
    public boolean addItinerary( Itinerary newItinerary){
        
        for( Itinerary itinerary : itineraries){
            
            if( itinerary.getId() == newItinerary.getId())
                return false;
        }
        
        itineraries.add(newItinerary);
        return true;
    }
    
    
    /**
     *  Tries to delete an existing itinerary from the list.
     * 
     *  @param  itineraryId ( the id of the itinerary in question)
     *  @return Boolean     ( true if the itinerary has been deleted, false otherwise)
     */
    @Override
    public boolean deleteItinerary( long itineraryId){
        
        Itinerary itinerary = readItinerary(itineraryId);
        
        if( itinerary == null) 
            return false;
        
        itineraries.remove(itinerary);
        return true;
    }
    
    
    /**
     *  Returns the whole itinerary list.
     * 
     *  @return itineraries   ( the whole list)
     */
    @Override
    public List<Itinerary> readItineraries(){
        
        return itineraries;
    }
    
    
    /**
     *  Tries to find a specific itinerary.
     * 
     *  @param  itineraryId ( the id of the itinerary in question)
     *  @return itinerary   ( returns null if the itinerary is not found, 
     *                          otherwise returns the itinerary object)
     */
    @Override
    public Itinerary readItinerary( long itineraryId){
        
        for ( Itinerary itinerary : itineraries){
            
            if ( itinerary.getId() == itineraryId)
                return itinerary;
        }
        
        return null; 
    }
    
    
    /**
     *  Tries to update the itinerary departure date.
     * 
     *  @param  itineraryId ( the id of the itinerary in question)
     *  @param  newDepDate  ( the new departure date to be set)
     *  @return Boolean     ( true if everything went well, false otherwise)
     */
    @Override
    public boolean updateItineraryDepDate( long itineraryId, String newDepDate){
        
        Itinerary itinerary = readItinerary(itineraryId);
        
        if( itinerary == null) 
            return false;
        
        itinerary.setDepDate(newDepDate);
        return true;
    }
    
    
    /**
     *  Tries to update the itinerary departure time.
     * 
     *  @param  itineraryId ( the id of the itinerary in question)
     *  @param  newDepTime  ( the new departure time to be set)
     *  @return Boolean     ( true if everything went well, false otherwise)
     */
    @Override
    public boolean updateItineraryDepTime( long itineraryId, String newDepTime){
        
        Itinerary itinerary = readItinerary(itineraryId);
        
        if( itinerary == null) 
            return false;
        
        itinerary.setDepTime(newDepTime);
        return true;
    }
    
    
    /**
     *  Tries to update the itinerary airline company.
     * 
     *  @param  itineraryId     ( the id of the itinerary in question)
     *  @param  newAirline      ( the new airline company to be set)
     *  @return Boolean         ( true if everything went well, false otherwise)
     */
    @Override
    public boolean updateItineraryAirline( long itineraryId, String newAirline){
        
        Itinerary itinerary = readItinerary(itineraryId);
        
        if( itinerary == null) 
            return false;
        
        itinerary.setAirline(newAirline);
        return true;
    }
    
    
    /**
     *  Tries to update the itinerary ticket price.
     * 
     *  @param  itineraryId     ( the id of the itinerary in question)
     *  @param  newPrice        ( the new ticket price to be set)
     *  @return Boolean         ( true if everything went well, false otherwise)
     */
    @Override
    public boolean updateItineraryPrice( long itineraryId, BigDecimal newPrice){
        
        Itinerary itinerary = readItinerary(itineraryId);
        
        if( itinerary == null) 
            return false;
        
        itinerary.setPrice(newPrice);
        return true;
    }
}