package com.example.demo.one2one.runner;

import com.example.demo.one2one.data.Post;
import com.example.demo.one2one.svc.PostService;
import com.example.demo.one2one.svc.PostStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Order(8)
@Component("delete-post")
public class DeleteRunner implements CommandLineRunner {
    @Autowired
    private PostService postService;
    @Autowired
    private PostStore postStore;
    @Override
    public void run(String... args) throws Exception {
        log.info("=========== Delete post ==========");
        //postStore.deleteById(1L);
        log.info("=========== Delete post end ==========");
        //postService.findAll().forEach(p -> log.info("post ==> {}", p.toString()));
        log.info("=========== Delete post check ==========");
    }
}
