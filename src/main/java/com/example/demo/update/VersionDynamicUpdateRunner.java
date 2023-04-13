package com.example.demo.update;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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

    @Override
    public void run(String... args) throws Exception {
        log.info("================== Version-DynamicUpdate601-Runner begin ==================");
        VersionDynamicUpdateObject o1 = new VersionDynamicUpdateObject("abc1", "data1");
        Long id = versionDynamicUpdateService.save(o1);

        // update  name=?, version=?
        VersionDynamicUpdateDTO d1 = VersionDynamicUpdateDTO.builder().Id(id).name("abc2").build();
        VersionDynamicUpdateObject v1 = versionDynamicUpdateStore.getOne(id);
        log.info("getById ==> {}", v1);
        v1 = versionDynamicUpdateMapper.mapToObject(v1, d1);
        versionDynamicUpdateService.save(v1);

        // update  data=?, name=?, version=?
        VersionDynamicUpdateDTO d2 = VersionDynamicUpdateDTO.builder().Id(id).name("name3").data("data3").build();
        VersionDynamicUpdateObject v2 = versionDynamicUpdateStore.getById(id);
        log.info("getById ==> {}", v2);
        v2 = versionDynamicUpdateMapper.mapToObject(v2, d2);
        versionDynamicUpdateService.save(v2);

        // update  name=?, version=? since "data" property value is not changed, actually.
        VersionDynamicUpdateDTO d3 = VersionDynamicUpdateDTO.builder().Id(id).name("name4").data("data3").build();
        VersionDynamicUpdateObject v3 = versionDynamicUpdateStore.getById(id);
        log.info("getById ==> {}", v3);
        v3 = versionDynamicUpdateMapper.mapToObject(v3, d3);
        versionDynamicUpdateService.save(v3);

        log.info("================== Version-DynamicUpdate601-Runner end ==================");
    }
}
