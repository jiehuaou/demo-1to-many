package com.example.demo.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerType {
    private String type;

    @Override
    public String toString() {
        return "CustomerType{" +
                "type='" + type + '\'' +
                '}';
    }
}
