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

@Slf4j
@Order(1)
@Component("create-ans")
public class Runner1Creator implements CommandLineRunner {

    @Autowired
    private MyService myService;

    @Override
    public void run(String... args) throws Exception {
        log.info("runner creator begin ====>");

        Answer ans1=new Answer();
        ans1.setAnswername("Java is a programming language");
        ans1.setPostedBy("Ravi Malik");

        Answer ans2=new Answer();
        ans2.setAnswername("Java is a platform");
        ans2.setPostedBy("Sudhir Kumar");

        Answer ans3=new Answer();
        ans3.setAnswername("Servlet is an Interface");
        ans3.setPostedBy("Jai Kumar");

        Answer ans4=new Answer();
        ans4.setAnswername("Servlet is an API");
        ans4.setPostedBy("Arun");

        ArrayList<Answer> list1=new ArrayList<Answer>();
        list1.add(ans1);
        list1.add(ans2);

        ArrayList<Answer> list2=new ArrayList<Answer>();
        list2.add(ans3);
        list2.add(ans4);

        Question question1=new Question();
        question1.setQname("What is Java?");
        question1.setAnswers(list1);

        Question question2=new Question();
        question2.setQname("What is Servlet?");
        question2.setAnswers(list2);

        int id1 = myService.create(question1);
        int id2 = myService.create(question2);

        log.info(" create Qid {} ", id1);
        log.info(" create Qid {} ", id2);

        log.info("runner creator end <====  \n");
    }
}
