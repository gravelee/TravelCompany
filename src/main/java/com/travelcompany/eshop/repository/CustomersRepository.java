package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.domain.CategoryType;
import com.travelcompany.eshop.domain.Customer;
import java.util.List;

/**
 *  This is the CustomerRepository interface, here we have all the methods that
 *  a CustomerRepository implementation needs to implement in order to have
 *  the appropriate logic.
 * 
 *  @author Grproth
 */
public interface CustomersRepository {
    
    boolean addCustomer ( final Customer customer);
    boolean deleteCustomer( final long customerId);
    List<Customer> readCustomers();
    Customer readCustomer( final long customerId);
    boolean updateCustomerName( final long customerId, final String newName);
    boolean updateCustomerEmail( final long customerId, final String newEmail);
    boolean updateCustomerAddress( final long customerId, 
            final String newAddress);
    boolean updateCustomerNationality( long customerId, 
            final String [] newNationality);
    boolean updateCustomerCategoryType( long customerId, 
            final CategoryType newCategoryType);
}
