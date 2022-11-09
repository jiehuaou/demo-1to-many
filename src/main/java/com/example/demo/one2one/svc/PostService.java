package com.example.demo.one2one.svc;

import com.example.demo.one2one.data.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class PostService {
    private final PostStore postStore;

    public PostService(PostStore postStore) {
        this.postStore = postStore;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public long create(Post post) {
        return postStore.save(post).getId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Post update(Post post) {
        return postStore.save(post);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Stream<Post> findAll() {
        Iterable<Post> all = postStore.findAll();
        return StreamSupport.stream(all.spliterator(), false);
    }
}
