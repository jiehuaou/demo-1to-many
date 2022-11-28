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

@Slf4j
@Order(101)
@Component("create-post")
public class Runner101Creator implements CommandLineRunner {
    @Autowired
    private PostService postService;
    @Autowired
    private PostStore postStore;

    @Override
    public void run(String... args) throws Exception {
        log.info("=========== create post ==========");
        Post post = new Post();
        post.setName("Hibernate Master Class");
        post.setOwner(new Owner(null, "albert", "123323323"));
        post.setCategory(new Category(null, "Java"));
        PostDetails details = new PostDetails();
        post.addDetails(details);
        postService.create(post);
        log.info("=========== create post end ==========");
        postService.findAll()
                .forEach(p -> log.info("post ==> {}", p.toString()));
        log.info("=========== create post check ==========");
    }
}
