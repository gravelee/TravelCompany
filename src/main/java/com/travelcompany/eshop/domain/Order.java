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
public class Order extends IdParser {
    
    private final long id;
    private final long customerId;
    private final long itineraryId;
    private final PaymentType paymentType;
    private final BigDecimal paymentAmount;
    
    public Order( final long id, final long customerId, final long itineraryId, 
            final PaymentType paymentType, final BigDecimal paymentAmount) {
        
        this.id = id;
        this.customerId = customerId;
        this.itineraryId = itineraryId;
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
    }
    
    /*
        We did override cause we inherit that method IdParser abstruct class.
    */
    @Override
    public final long getId() {
        
        return id;
    }
    
    public final long getCustomerId() {
        
        return customerId;
    }
    
    public final BigDecimal getPaymentAmount() {
        
        return paymentAmount;
    }
    
    public static final String header() {
        
        return "\n\nid,customerId,itineraryId,paymentType,paymentAmount\n\n";
    }
    
    @Override
    public final String toString() {
        
        return "" + id + "," + customerId + "," + itineraryId + "," 
           + paymentType + "," + GeneralUtility.formatBigDecimal(paymentAmount);
    }
}
