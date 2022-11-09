package com.example.demo.one2one.svc;

import com.example.demo.one2one.data.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostStore  extends CrudRepository<Post, Long> {
}
