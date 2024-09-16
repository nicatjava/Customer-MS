package com.example.customerms.service.impl;

import com.example.customerms.dao.entity.CustomerEntity;
import com.example.customerms.dao.repository.CustomerRepository;
import com.example.customerms.dto.CustomerRequestDto;
import com.example.customerms.dto.CustomerResponseDto;
import com.example.customerms.exception.CustomerNotFoundException;
import com.example.customerms.mapper.CustomerMapper;
import com.example.customerms.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDto> findAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toResponseDto)
                .toList();
    }

    @Override
    public CustomerResponseDto findById(Long id) {
        return customerMapper.toResponseDto(getCustomerById(id));
    }

    @Override
    public CustomerResponseDto save(CustomerRequestDto customerRequestDto) {
        CustomerEntity customerEntity = customerMapper.toEntity(customerRequestDto);
        return customerMapper.toResponseDto(customerRepository.save(customerEntity));
    }

    @Override
    public CustomerResponseDto update(Long id, CustomerRequestDto customerRequestDto) {
        CustomerEntity updatedCustomerEntity = getCustomerById(id);

        // Updating fields
        updatedCustomerEntity.setFullName(customerRequestDto.getFullName());
        updatedCustomerEntity.setAge(customerRequestDto.getAge());
        updatedCustomerEntity.setPin(customerRequestDto.getPin());
        updatedCustomerEntity.setBalance(customerRequestDto.getBalance());

        return customerMapper.toResponseDto(customerRepository.save(updatedCustomerEntity));
    }

    @Override
    public String delete(Long id) {
        CustomerEntity findCustomerById = getCustomerById(id);
        customerRepository.delete(findCustomerById);
        return "Deleted id: " + id;
    }

    private CustomerEntity getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public Double getCustomerBalance(Long customerId) {
        CustomerEntity findCustomerById = getCustomerById(customerId);
        return findCustomerById.getBalance();
    }

    @Override
    public void updateCustomerBalance(Long customerId, Double newBalance) {
        CustomerEntity findCustomerById = getCustomerById(customerId);
        findCustomerById.setBalance(newBalance);
        customerRepository.save(findCustomerById);
    }
}
