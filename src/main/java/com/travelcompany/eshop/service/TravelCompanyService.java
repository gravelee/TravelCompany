package com.travelcompany.eshop.service;

import com.travelcompany.eshop.domain.PaymentType;
import java.math.BigDecimal;

/**
 *  This is TravelCompanyService interface, here we have all the methods that
 *  a TravelCompanyService implementation needs to implement in order to have
 *  the appropriate logic. We update the logic if we need more functionality.
 * 
 *  @author Grproth
 */
public interface TravelCompanyService {
    
    void loadCustomersData();
    void loadItinerariesData();
    void loadOrdersData();
    
    void saveCustomersData();
    void saveItinerariesData();
    void saveOrdersData();
    
    boolean createNewOrder( final long customerId, final long itineraryId, 
            final PaymentType paymentType);
    
    void displayAllOrdersWithCosts();
    void displaySpecificItineraries( final String departure, 
            final String destination); // either of these can be null.
    void displayCustomers( final int fromTicketNumber, 
            final BigDecimal fromCost);  
            // the numbers can be 0 (0.0) but not null (BigDecimal).
    void displayAllCustomersWithoutOrders();
}
