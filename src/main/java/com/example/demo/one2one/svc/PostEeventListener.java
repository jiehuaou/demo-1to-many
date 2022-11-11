package com.example.demo.one2one.svc;

import com.example.demo.one2one.data.Post;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * monitor the event of post entity
 */
@Slf4j
public class PostEeventListener {
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Post post) {
        log.info("[Post AUDIT] add/update/delete complete for post: " + post.getId());
    }

    @PostLoad
    private void afterLoad(Post post) {
        log.info("[post AUDIT] post loaded from database: " + post.getId());
    }
}
