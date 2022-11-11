package com.example.demo.one2one.runner;

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

import java.util.Optional;

/**
 * update with detached object
 */
@Slf4j
@Order(7)
@Component("detached-update-post")
public class DetachedUpdateRunner implements CommandLineRunner {
    @Autowired
    private PostService postService;
    @Autowired
    private PostStore postStore;
    @Override
    public void run(String... args) throws Exception {

        log.info("=========== UPDATE detached post ==========");
        PostDetails postDetails = new PostDetails(1L, true);
        Owner owner = new Owner(1L, "abc@group.com", "1238888899");
        Post post = new Post(1L,1L, "other name", owner, postDetails);

        log.info("=========== UPDATE detached post begin ==========");
        postService.update(post);

        log.info("=========== UPDATE detached post end ==========");
        postService.findAll()
                .forEach(p -> log.info("post ==> {}", p.toString()));
        log.info("=========== UPDATE detached post check ==========");
    }
}
