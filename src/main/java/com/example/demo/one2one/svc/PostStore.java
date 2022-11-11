package com.example.demo.one2one.svc;

import com.example.demo.one2one.data.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostStore  extends JpaRepository<Post, Long> {
}
