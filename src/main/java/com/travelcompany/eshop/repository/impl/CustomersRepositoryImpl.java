package com.travelcompany.eshop.repository.impl;

import com.travelcompany.eshop.domain.CategoryType;
import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.repository.CustomersRepository;
import com.travelcompany.eshop.utility.GeneralUtility;
import java.util.ArrayList;
import java.util.List;

/**
 *  This is an implementation of the CustomersRepository interface.
 * 
 *  @author Grproth
 */
public class CustomersRepositoryImpl implements CustomersRepository {
    
    private final List<Customer> customers = new ArrayList<>();

    
    /**
     *  Tries to add a new customer to the list.
     * 
     *  @param   newCustomer ( the customer to be added)
     *  @return  Boolean     ( true if customer has been added, false otherwise)
     */
    @Override
    public boolean addCustomer( final Customer newCustomer) {
        
        for( Customer customer : customers) {
            
            if( customer.getId() == newCustomer.getId())
                return false;
        }
        
        customers.add(newCustomer);
        return true;
    }
    
    
    /**
     *  Tries to delete an existing customer from the list.
     * 
     *  @param customerId   ( the id of the customer in question)
     *  @return Boolean     ( true if customer has been deleted false otherwise)
     */
    @Override
    public boolean deleteCustomer( final long customerId) {
        
        Customer customer = readCustomer(customerId);
        
        if( customer == null) 
            return false;
        
        customers.remove(customer);
        return true;
    }
    
    
    /**
     *  Returns the whole customer list.
     * 
     *  @return customers   ( the whole list)
     */
    @Override
    public List<Customer> readCustomers() {
        
        return customers;
    }
    
    
    /**
     *  Tries to find a specific customer.
     * 
     *  @param customerId   ( the id of the customer in question)
     *  @return customer    ( returns null if the customer is not found, 
     *                          otherwise returns the customer object)
     */
    @Override
    public Customer readCustomer( final long customerId) {

        for ( Customer customer : customers) {
            
            if ( customer.getId() == customerId)
                return customer;
        }
        
        return null; 
    }
    
    
    /**
     *  Tries to update the customers name.
     * 
     *  @param  customerId  ( the id of the customer in question)
     *  @param  newName     ( the new name to be set)
     *  @return Boolean     ( true if everything went well, false otherwise)
     */
    @Override
    public boolean updateCustomerName( final long customerId, 
            final String newName) {
        
        Customer customer = readCustomer(customerId);
        
        if( customer == null) 
            return false;
        
        customer.setName(newName);
        return true;
    }
    
    
    /**
     *  Tries to update the customers email address.
     * 
     *  @param  customerId  ( the id of the customer in question)
     *  @param  newEmail    ( the new mail address to be set)
     *  @return Boolean     ( true if everything went well, false otherwise)
     */
    @Override
    public boolean updateCustomerEmail( final long customerId, 
            final String newEmail) {
        
        Customer theCustomer = readCustomer(customerId);
        
        if( theCustomer == null) 
            return false;
        
        for ( Customer customer : customers) {
            
            if ( customer.getEmail() != null 
                && customer.getEmail().equals(newEmail))
                   return false;
        }
        
        theCustomer.setEmail(newEmail);
        return true;
    }
    
    
    /**
     *  Tries to update the customers real address.
     * 
     *  @param  customerId  ( the id of the customer in question)
     *  @param  newAddress  ( the new address to be set)
     *  @return Boolean     ( true if everything went well, false otherwise)
     */
    @Override
    public boolean updateCustomerAddress( final long customerId, 
            final String newAddress) {
        
        Customer theCustomer = readCustomer(customerId);
        
        if( theCustomer == null) 
            return false;
        
        if( !GeneralUtility.isValidAddress(newAddress))
            return false;
           
        theCustomer.setAddress(newAddress);
        return true;
    }
    
    
    /**
     *  Tries to update the customers nationality.
     * 
     *  @param  customerId      ( the id of the customer in question)
     *  @param  newNationality  ( the new nationality to be set)
     *  @return Boolean         ( true if everything went well, false otherwise)
     */
    @Override
    public boolean updateCustomerNationality( final long customerId, 
            final String [] newNationality) {
        
        Customer theCustomer = readCustomer(customerId);
        
        if( theCustomer == null) 
            return false;
        
        theCustomer.setNationality(newNationality);
        return true;
    }
    
    
    /**
     *  Tries to update the customers categoryType.
     * 
     *  @param  customerId      ( the id of the customer in question)
     *  @param  newCategoryType ( the new categoryType to be set)
     *  @return Boolean         ( true if everything went well, false otherwise)
     */
    @Override
    public boolean updateCustomerCategoryType( 
            final long customerId, final CategoryType newCategoryType) {
        
        Customer theCustomer = readCustomer(customerId);
        
        if( theCustomer == null) 
            return false;
        
        theCustomer.setCategoryType(newCategoryType);
        return true;
    }
}
