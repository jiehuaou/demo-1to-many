package com.example.demo.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VersionDynamicUpdateService {


    @Autowired
    private VersionDynamicUpdateStore versionDynamicUpdateStore;

    @Transactional(propagation = Propagation.REQUIRED)
    public VersionDynamicUpdateObject save(VersionDynamicUpdateObject versionDynamicUpdateObject) {
        return versionDynamicUpdateStore.save(versionDynamicUpdateObject);
    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    public Long saveDTO(VersionDynamicUpdateDTO versionDynamicUpdateDTO) {
//        VersionDynamicUpdateObject versionDynamicUpdateObject = versionDynamicUpdateStore.getById(versionDynamicUpdateDTO.getId());
//        versionDynamicUpdateObject = versionDynamicUpdateMapper.mapToObject(versionDynamicUpdateObject, versionDynamicUpdateDTO);
//        return versionDynamicUpdateStore.save(versionDynamicUpdateObject).getId();
//    }


}
