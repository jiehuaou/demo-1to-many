package com.example.demo.update;

import lombok.Builder;
import lombok.Data;


@Data
@Builder()
public class VersionDynamicUpdateDTO {
    private Long Id;
    private String name;
    private String data;

}
