package com.example.demo.many2many.runner;

import com.example.demo.many2many.data.ManyAnswer;
import com.example.demo.many2many.data.ManyQuestion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Order(3)
@Component
public class CreatorRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ManyAnswer an1=new ManyAnswer();
        an1.setAnswername("Java is a programming language");
        an1.setPostedBy("Ravi Malik");

        ManyAnswer an2=new ManyAnswer();
        an2.setAnswername("Java is a platform");
        an2.setPostedBy("Sudhir Kumar");

        ManyQuestion q1=new ManyQuestion();
        q1.setQname("What is Java?");
        ArrayList<ManyAnswer> l1=new ArrayList<ManyAnswer>();
        l1.add(an1);
        l1.add(an2);
        q1.setAnswers(l1);


        ManyAnswer ans3=new ManyAnswer();
        ans3.setAnswername("Servlet is an Interface");
        ans3.setPostedBy("Jai Kumar");

        ManyAnswer ans4=new ManyAnswer();
        ans4.setAnswername("Servlet is an API");
        ans4.setPostedBy("Arun");

        ManyQuestion q2=new ManyQuestion();
        q2.setQname("What is Servlet?");
        ArrayList<ManyAnswer> l2=new ArrayList<ManyAnswer>();
        l2.add(ans3);
        l2.add(ans4);
        q2.setAnswers(l2);
    }
}
