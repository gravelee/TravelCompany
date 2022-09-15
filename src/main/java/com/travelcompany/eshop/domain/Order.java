
package com.travelcompany.eshop.domain;

import com.travelcompany.eshop.utility.GeneralUtility;
import java.math.BigDecimal;

/**
 *
 * A class that represent the different orders and their info.
 * 
 * @author Grproth
 */
public class Order implements IdParser{
    
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
    
    public long getId(){
        
        return id;
    }
    
    public long getCustomerId(){
        
        return customerId;
    }
    
    public BigDecimal getPaymentAmount(){
        
        return paymentAmount;
    }
    
    public static String header(){
        
        return "\nid,customerId,itineraryId,paymentType,paymentAmount\n\n";
    }
    
    @Override
    public String toString(){
        
        return "" + id + "," + customerId + "," + itineraryId + "," 
                + paymentType + "," + GeneralUtility.formatBigDecimal(paymentAmount);
    }
}
