package com.example.demo.one2many.runner;

import com.example.demo.one2many.data.Question;
import com.example.demo.one2many.svc.MyService;
import com.example.demo.one2many.svc.MyStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

@Slf4j
@Order(2)
@Component
public class QueryRunner  implements CommandLineRunner {
    @Autowired
    private MyStore myStore;
    @Override
    public void run(String... args) throws Exception {
        log.info("runner Query begin ====>");
        Iterable<Question> all = myStore.findAll();
        StreamSupport.stream(all.spliterator(), false)
                .peek(q->log.info("Qid --> {}", q.getId()))
                .flatMap(q->q.getAnswers().stream())
                .forEach(a->log.info(a.toString()));
        log.info("runner Query end <====  \n");
    }
}
