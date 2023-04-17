package com.example.demo.update;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EM.getReference() is EQ to JPA.getOne() and JPA.getById()
 * they all return proxy whose state will be fetched only when non-PK field is accessed.
 *
 * JPA.findByXXX always call database then return the real entity.
 */
@Slf4j
@Order(601)
@Component("Version-DynamicUpdate601-Runner")
public class VersionDynamicUpdateRunner implements CommandLineRunner {

    @Autowired
    VersionDynamicUpdateService versionDynamicUpdateService;

    @Autowired
    VersionDynamicUpdateMapper versionDynamicUpdateMapper;

    @Autowired
    private VersionDynamicUpdateStore versionDynamicUpdateStore;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {
        log.info("================== Version-DynamicUpdate601-Runner begin ==================");
        VersionDynamicUpdateObject o1 = new VersionDynamicUpdateObject("abc1", "data1");
        Long id = versionDynamicUpdateService.save(o1).getId();

        // get reference
        // update  name=?, version=?
        VersionDynamicUpdateDTO d2 = VersionDynamicUpdateDTO.builder().Id(id).name("abc2").build();
        VersionDynamicUpdateObject v2 = versionDynamicUpdateStore.getOne(id);
        log.info("getById ==> {}", v2);
        v2 = versionDynamicUpdateMapper.mapToObject(v2, d2);
        versionDynamicUpdateService.save(v2);

        // get reference
        // update  data=?, name=?, version=?
        VersionDynamicUpdateDTO d3 = VersionDynamicUpdateDTO.builder().Id(id).name("name3").data("data3").build();
        VersionDynamicUpdateObject v3 = versionDynamicUpdateStore.getById(id);
        log.info("getById ==> {}", v3);
        v3 = versionDynamicUpdateMapper.mapToObject(v3, d3);
        versionDynamicUpdateService.save(v3);

        // return real entity
        // update  name=?, version=? since "data" property value is not changed, actually.
        VersionDynamicUpdateDTO d4 = VersionDynamicUpdateDTO.builder().Id(id).name("name4").data("data3").build();
        VersionDynamicUpdateObject v4 = versionDynamicUpdateStore.findById(id).get();
        log.info("getById ==> {}", v4);
        v4 = versionDynamicUpdateMapper.mapToObject(v4, d4);
        Long version = versionDynamicUpdateService.save(v4).getVersion();

        // modify transient, will overwrite all property, which is not correct.
        VersionDynamicUpdateObject v5 = new VersionDynamicUpdateObject();
        v5.setId(id);
        v5.setVersion(version);
        v5.setData("data5");
        version = versionDynamicUpdateService.save(v5).getVersion();

        // get reference
        //VersionDynamicUpdateObject v6 = versionDynamicUpdateService.onlyChangeDataField(id, "data6");
        VersionDynamicUpdateObject v6 = entityManager.getReference(VersionDynamicUpdateObject.class, id);
        v6.setData("data6");
        v6 = versionDynamicUpdateService.save(v6);


        log.info("================== Version-DynamicUpdate601-Runner end ==================");
        log.info("final object {}", versionDynamicUpdateStore.getById(id));
        log.info("================== Version-DynamicUpdate601-Runner end ==================");
    }
}
