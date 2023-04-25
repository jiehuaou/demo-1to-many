package com.example.demo.github.test01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * stackoverflow question 75394989 solution
 * https://stackoverflow.com/questions/75394989
 */
@Slf4j
@Order(1)
@Service("Test01Runner")
public class Test01Runner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        create();
        delete();

    }

    @Transactional(propagation = Propagation.REQUIRED)
    void create() {
        User user = new User("abc");
        Category00 category = new Category00("label");
        user.addCategory(category);
        category.addUser(user);
        user = userRepository.save(user);
        log.info("saved {}", user);
        userRepository.findAll().forEach(e->log.info("{}", e));
        categoryRepository.findAll().forEach(e->log.info("{}", e));
        log.info("=====================================================");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void delete() {
        Category00 category = categoryRepository.findByUsers_Name("abc");
        categoryRepository.delete(category);
        log.info("deleted {}", category);
        log.info("categoryRepository.count() = {}", categoryRepository.count());
        userRepository.findAll().forEach(e->log.info("{}", e));
        categoryRepository.findAll().forEach(e->log.info("{}", e));
    }
}
