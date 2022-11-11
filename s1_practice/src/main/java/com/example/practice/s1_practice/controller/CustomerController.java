package com.example.practice.s1_practice.controller;

import com.example.practice.s1_practice.domain.Customer;
import com.example.practice.s1_practice.exception.CustomerNotFoundException;
import com.example.practice.s1_practice.services.CustomerService;
import com.example.practice.s1_practice.services.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController {

    private CustomerService customerService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public CustomerController(CustomerService customerService, SecurityTokenGenerator securityTokenGenerator){
        this.customerService = customerService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {

        Map<String , String > map = new HashMap<>();
        try {
            Customer customer1 =customerService.findByCustomerNameAndCustomerPassword(customer.getCustomerName(),customer.getCustomerPassword());
            if(customer1.getCustomerName().equals(customer.getCustomerName())){
                map = securityTokenGenerator.generateToken(customer);
            }
            return new ResponseEntity<>(map,HttpStatus.OK);
        }catch (CustomerNotFoundException ex){
            throw  new CustomerNotFoundException();
        }catch (Exception e){
            return new ResponseEntity<>("Try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> insertCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.addCustomer(customer),HttpStatus.CREATED);
    }

    @GetMapping("/custdata/v1/custs")
    public ResponseEntity<?> getCustomer(){
        return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
    }

    @DeleteMapping("/custdata/v1/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable int customerId) throws CustomerNotFoundException {
        ResponseEntity responseEntity;
        try {
            customerService.deleteCustomer(customerId);
            responseEntity = new ResponseEntity<>("Successfully deleted customer",HttpStatus.OK);
        }catch (CustomerNotFoundException ex){
            throw new CustomerNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
