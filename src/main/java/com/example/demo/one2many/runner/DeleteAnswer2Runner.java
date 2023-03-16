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
 * delete Answer (Owner-side) by calling store.delete;
 *
 * SQL : delete from answer_table where id=?
 * 
 */
@Slf4j
@Order(1005)
@Component("delete-ans-1005")
public class DeleteAnswer2Runner implements CommandLineRunner {

    @Autowired
    private MyService myService;
    @Autowired
    private QuestionStore questionStore;
    @Autowired
    private AnswerStore answerStore;

    @Override
    public void run(String... args) throws Exception {
        log.info("delete Answer begin ====>");

        Optional<Answer> ans1 = answerStore.findByAnswername("Servlet is an Interface");
        if(ans1.isPresent()) {
            myService.deleteAnswer(ans1.get());
        }

        log.info("delete Answer end <====  \n");
        log.info("=============================================>");
        List<Question> all = myService.queryQuestion();

        all.stream()
                .peek(q->log.info("Question --> {}", q.toString()))
                .flatMap(q->q.getAnswers().stream())
                .forEach(a->log.info("Answer --> {}", a.toString()));
        log.info("runner Question Query end <====  \n");
    }
}
