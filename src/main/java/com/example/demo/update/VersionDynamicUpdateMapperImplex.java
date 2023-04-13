package com.example.demo.update;

//import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

//@Component
public class VersionDynamicUpdateMapperImplex implements VersionDynamicUpdateMapper{
    //private final ModelMapper modelMapper;

//    public VersionDynamicUpdateMapperImpl(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

    @Override
    public VersionDynamicUpdateObject mapToObject(VersionDynamicUpdateObject versionDynamicUpdateObject, VersionDynamicUpdateDTO versionDynamicUpdateDTO) {
        if (versionDynamicUpdateDTO.getData()!=null) {
            versionDynamicUpdateObject.setData(versionDynamicUpdateDTO.getData());
        }
        if (versionDynamicUpdateDTO.getName()!=null) {
            versionDynamicUpdateObject.setName(versionDynamicUpdateDTO.getName());
        }
        return versionDynamicUpdateObject;
    }

    @Override
    public VersionDynamicUpdateDTO mapFromObject(VersionDynamicUpdateObject versionDynamicUpdateObject) {
        return VersionDynamicUpdateDTO.builder().Id(versionDynamicUpdateObject.getId())
                .name(versionDynamicUpdateObject.getName())
                .data(versionDynamicUpdateObject.getData())
                .build();
        // modelMapper.map(versionDynamicUpdateObject, VersionDynamicUpdateDTO.class);
    }
}
