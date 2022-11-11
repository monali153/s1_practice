package com.example.practice.s1_practice.services;

import com.example.practice.s1_practice.domain.Customer;
import com.example.practice.s1_practice.exception.CustomerNotFoundException;
import com.example.practice.s1_practice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException {
        boolean result = false;

        if(customerRepository.findById(customerId).isEmpty()){
            throw new CustomerNotFoundException();
        }else {
            customerRepository.deleteById(customerId);
            result = true;
        }
        return result;
    }

    @Override
    public Customer findByCustomerNameAndCustomerPassword(String customerName, String customerPassword) throws CustomerNotFoundException {

        Customer customer = customerRepository.findByCustomerNameAndCustomerPassword(customerName,customerPassword);

        if(customer == null){
            throw new CustomerNotFoundException();
        }
        return customer;
    }
}
