package com.example.demo.many2many.runner;

import com.example.demo.many2many.data.Tag;
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
        Tag an1=new Tag();
        an1.setAnswername("Java is a programming language");
        an1.setPostedBy("Ravi Malik");

        Tag an2=new Tag();
        an2.setAnswername("Java is a platform");
        an2.setPostedBy("Sudhir Kumar");

        ManyQuestion q1=new ManyQuestion();
        q1.setQname("What is Java?");
        ArrayList<Tag> l1=new ArrayList<Tag>();
        l1.add(an1);
        l1.add(an2);
        q1.setAnswers(l1);


        Tag ans3=new Tag();
        ans3.setAnswername("Servlet is an Interface");
        ans3.setPostedBy("Jai Kumar");

        Tag ans4=new Tag();
        ans4.setAnswername("Servlet is an API");
        ans4.setPostedBy("Arun");

        ManyQuestion q2=new ManyQuestion();
        q2.setQname("What is Servlet?");
        ArrayList<Tag> l2=new ArrayList<Tag>();
        l2.add(ans3);
        l2.add(ans4);
        q2.setAnswers(l2);
    }
}
