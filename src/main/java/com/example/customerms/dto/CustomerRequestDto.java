package com.example.customerms.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {

    private String fullName;
    private Integer age;
    private String pin;
    private Double balance;
}
