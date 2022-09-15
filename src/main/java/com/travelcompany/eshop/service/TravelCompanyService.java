package com.travelcompany.eshop.service;

import com.travelcompany.eshop.domain.Order;
import com.travelcompany.eshop.domain.PaymentType;
import java.math.BigDecimal;

/**
 *
 * @author Grproth
 */
public interface TravelCompanyService {
    
    void loadCustomersData();
    void loadItinerariesData();
    void loadOrdersData();
    
    void saveCustomersData();
    void saveItinerariesData();
    void saveOrdersData();
    
    boolean createNewOrder( long customerId, long itineraryId, PaymentType paymentType);
    
    void displayAllOrdersWithCosts();
    void displaySpecificItineraries( String departure, String destination); // either of these can be null.
    void displayCustomers( int fromTicketNumber, BigDecimal fromCost);  // the numbers can be 0 (0.0) but not null (BigDecimal).
    void displayAllCustomersWithoutOrders();
}
