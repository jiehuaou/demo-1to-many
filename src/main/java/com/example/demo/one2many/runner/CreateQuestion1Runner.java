package com.example.demo.one2many.runner;

import com.example.demo.one2many.data.Answer;
import com.example.demo.one2many.data.Question;
import com.example.demo.one2many.svc.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * create new question (Inverse-side) with new Answer (Owner-side);
 *
 * usually save Inverse-side object;
 *
 */
@Slf4j
@Order(1001)
@Component("create-ans")
public class CreateQuestion1Runner implements CommandLineRunner {

    @Autowired
    private MyService myService;

    @Override
    public void run(String... args) throws Exception {
        log.info("create Question & Answer begin ====>");

        Answer ans1=new Answer();
        ans1.setAnswername("Java is a programming language");
        ans1.setPostedBy("Ravi Malik");

        Answer ans2=new Answer();
        ans2.setAnswername("Java is a platform");
        ans2.setPostedBy("Sudhir Kumar");

        Question question1=new Question();
        question1.setQname("What is Java?");
        question1.addAnswer(ans1);
        question1.addAnswer(ans2);

        Answer ans3=new Answer();
        ans3.setAnswername("Servlet is an Interface");
        ans3.setPostedBy("Jai Kumar");

        Answer ans4=new Answer();
        ans4.setAnswername("Servlet is an API");
        ans4.setPostedBy("Arun");

        Question question2=new Question();
        question2.setQname("What is Servlet?");
        question2.addAnswer(ans3);
        question2.addAnswer(ans4);

        int id1 = myService.save(question1);
        int id2 = myService.save(question2);

        log.info(" create Qid {} ", id1);
        log.info(" create Qid {} ", id2);

        log.info("create Question & Answer end <====  \n");
        log.info("=============================================>");
        List<Question> all = myService.queryQuestion();

        all.stream()
                .peek(q->log.info("Question --> {}", q.toString()))
                .flatMap(q->q.getAnswers().stream())
                .forEach(a->log.info("Answer --> {}", a.toString()));
        log.info("runner Question Query end <====  \n");
    }
}
