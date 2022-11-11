package com.example.practice.s1_practice.services;

import com.example.practice.s1_practice.domain.Customer;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String, String> generateToken(Customer customer);
}
