package com.example.practice.s1_practice.services;

import com.example.practice.s1_practice.domain.Customer;
import com.example.practice.s1_practice.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {

    public Customer addCustomer(Customer customer);
    public List<Customer> getAllCustomers();
    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException;

    public Customer findByCustomerNameAndCustomerPassword(String customerName, String customerPassword) throws CustomerNotFoundException;
}
