
package com.travelcompany.eshop.model;

import com.travelcompany.eshop.util.HelperMethods;
import java.math.BigDecimal;

/**
 *
 * A class that represent the different orders and their info.
 * 
 * @author Grproth
 */
public class Order {
    
    private long id;
    private long customerId;
    private long itineraryId;
    private PaymentType paymentType;
    private BigDecimal paymentAmount;
    
    public Order(long id, long customerId, long itineraryId, 
            PaymentType paymentType, BigDecimal paymentAmount){
        
        this.id = id;
        this.customerId = customerId;
        this.itineraryId = itineraryId;
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
    }
    
    @Override
    public String toString(){
        
        
        
        return "" + id + "," + customerId + "," + itineraryId + "," 
                + paymentType + "," + HelperMethods.formatBigDecimal(paymentAmount);
    }
}
