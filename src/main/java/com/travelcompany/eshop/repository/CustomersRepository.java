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
    
    boolean addCustomer ( Customer customer);
    boolean deleteCustomer( long customerId);
    List<Customer> readCustomers();
    Customer readCustomer( long customerId);
    boolean updateCustomerName( long customerId, String newName);
    boolean updateCustomerEmail( long customerId, String newEmail);
    boolean updateCustomerAddress( long customerId, String newAddress);
    boolean updateCustomerNationality( long customerId, String [] newNationality);
    boolean updateCustomerCategoryType( long customerId, CategoryType newCategoryType);
    
}
