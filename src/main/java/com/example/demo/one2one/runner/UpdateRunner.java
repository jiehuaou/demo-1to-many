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
@Order(6)
@Component("update-post")
public class UpdateRunner implements CommandLineRunner {
    @Autowired
    private PostService postService;
    @Autowired
    private PostStore postStore;
    @Override
    public void run(String... args) throws Exception {
        log.info("=========== UPDATE post ==========");
        Optional<Post> post = postStore.findById(1L);
        post.ifPresentOrElse(post1 -> {
            log.info("=========== UPDATE post begin ==========");
            post1.setName("new name");
            post1.getDetails().setVisible(true);
            postService.update(post1);
        }, ()->{
            log.info("post id {} ===> not found");
        });
        log.info("=========== UPDATE post end ==========");
        postService.findAll()
                .forEach(p -> log.info("post ==> {}", p.toString()));
        log.info("=========== UPDATE post check ==========");
    }
}
