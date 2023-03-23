package com.example.demo.one2many.runner;

import com.example.demo.one2many.data.Answer;
import com.example.demo.one2many.data.Question;
import com.example.demo.one2many.svc.QAService;
import com.example.demo.one2many.svc.QuestionStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * create new Answer (Owner-side) to existing question (Inverse-side) with ;
 *
 * usually save Inverse-side object;
 * 
 */
@Slf4j
@Order(1002)
@Component("create-ans-1002")
public class CreateAnswer1Runner implements CommandLineRunner {

    @Autowired
    private QAService QAService;
    @Autowired
    private QuestionStore questionStore;

    @Override
    public void run(String... args) throws Exception {
        log.info("create new Answer begin ====>");

        Answer ans1=new Answer();
        ans1.setAnswername("Java is very nice");
        ans1.setPostedBy("Steve Jobs");

        questionStore.findByQname("What is Java?").ifPresent(question -> {
            question.addAnswer(ans1);
            QAService.save(question);
            log.info(" save question {} ", question.toString());
        });

        log.info("create new Answer end <====  \n");
        log.info("=============================================>");
        List<Question> all = QAService.queryQuestion();

        all.stream()
                .peek(q->log.info("Question --> {}", q.toString()))
                .flatMap(q->q.getAnswers().stream())
                .forEach(a->log.info("Answer --> {}", a.toString()));
        log.info("runner Question Query end <====  \n");
    }
}
