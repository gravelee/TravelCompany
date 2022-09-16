
package com.travelcompany.eshop.domain;

import com.travelcompany.eshop.utility.GeneralUtility;
import java.math.BigDecimal;

/**
 *  A class that represent the different orders and their info.
 *  We do extend from IdParser in order to reduce the number of methods called
 *  within GeneralUtility class.
 * 
 *  @author Grproth
 */
public class Order extends IdParser{
    
    private final long id;
    private final long customerId;
    private final long itineraryId;
    private final PaymentType paymentType;
    private final BigDecimal paymentAmount;
    
    public Order(long id, long customerId, long itineraryId, 
            PaymentType paymentType, BigDecimal paymentAmount){
        
        this.id = id;
        this.customerId = customerId;
        this.itineraryId = itineraryId;
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
    }
    
    /*
        We did the override cause we inherit that method from IdParser abstruct class.
    */
    @Override
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
        
        return "\n\nid,customerId,itineraryId,paymentType,paymentAmount\n\n";
    }
    
    @Override
    public String toString(){
        
        return "" + id + "," + customerId + "," + itineraryId + "," 
                + paymentType + "," + GeneralUtility.formatBigDecimal(paymentAmount);
    }
}
