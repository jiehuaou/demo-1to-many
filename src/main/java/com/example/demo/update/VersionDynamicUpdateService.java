package com.example.demo.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class VersionDynamicUpdateService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private VersionDynamicUpdateStore versionDynamicUpdateStore;

    @Transactional(propagation = Propagation.REQUIRED)
    public VersionDynamicUpdateObject save(VersionDynamicUpdateObject versionDynamicUpdateObject) {
        return versionDynamicUpdateStore.save(versionDynamicUpdateObject);
    }

    /**
     * use reference, should only update modified fields
     * update  set data=?, version=?
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public VersionDynamicUpdateObject onlyChangeDataField(Long id, String data) {
        VersionDynamicUpdateObject vo = entityManager.getReference(VersionDynamicUpdateObject.class, id);
        vo.setData(data);
        return versionDynamicUpdateStore.save(vo);
    }


}
