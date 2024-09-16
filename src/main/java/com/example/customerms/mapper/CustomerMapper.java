package com.example.customerms.mapper;

import com.example.customerms.dao.entity.CustomerEntity;
import com.example.customerms.dto.CustomerRequestDto;
import com.example.customerms.dto.CustomerResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDto toResponseDto(CustomerEntity customerEntity);
    CustomerEntity toEntity(CustomerRequestDto dto);

}
