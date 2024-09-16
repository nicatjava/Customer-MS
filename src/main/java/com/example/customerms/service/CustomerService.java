package com.example.customerms.service;

import com.example.customerms.dto.CustomerRequestDto;
import com.example.customerms.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDto> findAll();
    CustomerResponseDto findById(Long id);
    CustomerResponseDto save(CustomerRequestDto customerRequestDto);
    CustomerResponseDto update(Long id, CustomerRequestDto customerRequestDto);
    String delete(Long id);

    Double getCustomerBalance(Long customerId);
    void updateCustomerBalance(Long customerId, Double newBalance);
}
