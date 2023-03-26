package com.example.demo.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * this is non-entity class
 */
@Data
public class CustomerTypeCount {
    private String customerType;
    private Long count;

    public CustomerTypeCount(String customerType, Long count) {
        this.customerType = customerType;
        this.count = count;
    }
}
