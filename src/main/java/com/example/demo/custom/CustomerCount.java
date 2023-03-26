package com.example.demo.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerCount {
    private Long count;

    @Override
    public String toString() {
        return "CustomerCount{" +
                "count=" + count +
                '}';
    }
}
