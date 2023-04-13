package com.example.demo.update;


import org.mapstruct.*;

/**
 * demo : use mapStruct to map property between entity and DTO
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VersionDynamicUpdateMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "name", source = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "data", source = "data", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VersionDynamicUpdateObject mapToObject(
            @MappingTarget
            VersionDynamicUpdateObject versionDynamicUpdateObject,
                                           VersionDynamicUpdateDTO versionDynamicUpdateDTO);
    VersionDynamicUpdateDTO mapFromObject(VersionDynamicUpdateObject versionDynamicUpdateObject);
}
