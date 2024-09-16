package com.example.customerms.controller;

import com.example.customerms.dto.CustomerRequestDto;
import com.example.customerms.dto.CustomerResponseDto;
import com.example.customerms.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerResponseDto> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getCustomerById(@PathVariable("id") Long id) {
        return customerService.findById(id);
    }

    @PostMapping
    public CustomerResponseDto createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.save(customerRequestDto);
    }

    @PutMapping("/{id}")
    public CustomerResponseDto updateCustomer(@PathVariable("id") Long id,
                                              @RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.update(id, customerRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        return customerService.delete(id);
    }

    @GetMapping("/{id}/balance")
    public Double getBalance(@PathVariable("id") Long id) {
        return customerService.getCustomerBalance(id);
    }

    @PutMapping("/{customerId}/balance")
    void updateCustomerBalance(@PathVariable("customerId") Long customerId, @RequestParam Double newBalance){
        customerService.updateCustomerBalance(customerId, newBalance);
    }
}
