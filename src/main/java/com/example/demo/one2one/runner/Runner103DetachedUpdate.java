package com.example.demo.one2one.runner;

import com.example.demo.one2one.data.Category;
import com.example.demo.one2one.data.Owner;
import com.example.demo.one2one.data.Post;
import com.example.demo.one2one.data.PostDetails;
import com.example.demo.one2one.svc.PostService;
import com.example.demo.one2one.svc.PostStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * update with detached object
 */
@Slf4j
@Order(103)
@Component("detached-update-post")
public class Runner103DetachedUpdate implements CommandLineRunner {
    @Autowired
    private PostService postService;
    @Autowired
    private PostStore postStore;
    @Override
    public void run(String... args) throws Exception {
        // if(true) return;
        log.info("=========== UPDATE detached post ==========");
        PostDetails postDetails = new PostDetails(1L, true);
        Owner owner = new Owner(1L, "abc@group.com", "1238888899");
        Category category = new Category(8L, "Java 11");
        Post post = new Post(1L,1L, "other Hibernate", owner, postDetails, category);

        log.info("=========== UPDATE detached post begin ==========");
        postService.update(post);

        log.info("=========== UPDATE detached post end ==========");
        postService.findAll()
                .forEach(p -> log.info("post ==> {}", p.toString()));
        log.info("=========== UPDATE detached post check ==========");
    }
}
