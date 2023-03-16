package com.example.demo.one2many.runner;

import com.example.demo.one2many.data.Answer;
import com.example.demo.one2many.data.Question;
import com.example.demo.one2many.svc.AnswerStore;
import com.example.demo.one2many.svc.MyService;
import com.example.demo.one2many.svc.QuestionStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * move Answer (Owner-side, child) to another question (Inverse-side, Parent) with ;
 *
 * usually save Inverse-side (Parent-side) object;
 *
 * sql : update answer_table set answername=?, posted_by=?, question_id=? where id=?
 * 
 */
@Slf4j
@Order(1003)
@Component("move-answer-1003")
public class MoveAnswer1Runner implements CommandLineRunner {

    @Autowired
    private MyService myService;
    @Autowired
    private QuestionStore questionStore;
    @Autowired
    private AnswerStore answerStore;

    @Override
    public void run(String... args) throws Exception {
        log.info("move Answer begin ====>");

        Optional<Answer> ans1 = answerStore.findByAnswername("Java is very nice");
        Optional<Question> quest = questionStore.findByQname("What is Servlet?");
        if(ans1.isPresent() && quest.isPresent()) {
            ans1.get().setAnswername("Java Servlet is very nice");
            quest.get().addAnswer(ans1.get());
            myService.save(quest.get());
        }

        log.info("move Answer end <====  \n");
        log.info("=============================================>");
        List<Question> all = myService.queryQuestion();

        all.stream()
                .peek(q->log.info("Question --> {}", q.toString()))
                .flatMap(q->q.getAnswers().stream())
                .forEach(a->log.info("Answer --> {}", a.toString()));
        log.info("runner Question Query end <====  \n");
    }
}
