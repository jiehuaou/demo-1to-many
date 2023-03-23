package com.example.demo.one2many.runner;

import com.example.demo.one2many.data.Question;
import com.example.demo.one2many.svc.QAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Order(10010)
@Component("query-ans")
public class Runner2Query implements CommandLineRunner {

    @Autowired
    private QAService QAService;


    @Override
    public void run(String... args) throws Exception {
        log.info("runner Question Query begin ====>");
        List<Question> all = QAService.queryQuestion();

        all.stream()
                .peek(q->log.info("Question --> {}", q.toString()))
                .flatMap(q->q.getAnswers().stream())
                .forEach(a->log.info("Answer --> {}", a.toString()));
        log.info("runner Question Query end <====  \n");
    }
}
