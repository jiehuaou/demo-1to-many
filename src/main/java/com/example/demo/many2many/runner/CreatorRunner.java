package com.example.demo.many2many.runner;

import com.example.demo.many2many.data.Tag;
import com.example.demo.many2many.data.Tutorial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Order(3)
@Component("CreatorTutorial")
public class CreatorRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Tag an1=new Tag("java");
        Tag an2=new Tag("web");

        Tutorial q1=new Tutorial("spring", "java platform");
        q1.addTag(an1);
        q1.addTag(an2);


        Tag ans3=new Tag("python");
        Tag ans4=new Tag("AI");

        Tutorial q2=new Tutorial("pytoch", "AI tool");
        q2.addTag(ans3);
        q2.addTag(ans4);
    }
}
