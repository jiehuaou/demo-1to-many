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
    private VersionDynamicUpdateStore versionDynamicUpdateStore;

    @Override
    public void run(String... args) throws Exception {
        log.info("================== Version-DynamicUpdate601-Runner begin ==================");
        VersionDynamicUpdateObject o1 = new VersionDynamicUpdateObject("abc1", "data1");
        Long id = versionDynamicUpdateService.save(o1);

        VersionDynamicUpdateDTO d1 = VersionDynamicUpdateDTO.builder().Id(id).name("abc2").build();
        versionDynamicUpdateService.saveDTO(d1);

        VersionDynamicUpdateDTO d2 = VersionDynamicUpdateDTO.builder().Id(id).name("name3").data("data3").build();
        versionDynamicUpdateService.saveDTO(d2);

        VersionDynamicUpdateDTO d3 = VersionDynamicUpdateDTO.builder().Id(id).name("name4").data("data3").build();
        versionDynamicUpdateService.saveDTO(d3);

        log.info("================== Version-DynamicUpdate601-Runner end ==================");
    }
}
