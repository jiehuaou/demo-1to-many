package com.example.demo.update;


public interface VersionDynamicUpdateMapper {
    VersionDynamicUpdateObject mapToObject(VersionDynamicUpdateObject versionDynamicUpdateObject, VersionDynamicUpdateDTO versionDynamicUpdateDTO);
    VersionDynamicUpdateDTO mapFromObject(VersionDynamicUpdateObject versionDynamicUpdateObject);
}
