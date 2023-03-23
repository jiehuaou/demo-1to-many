package com.example.demo.one2many.runner;

import com.example.demo.one2many.data.Question;
import com.example.demo.one2many.svc.AnswerStore;
import com.example.demo.one2many.svc.QAService;
import com.example.demo.one2many.svc.QuestionStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * delete Question (Inverse-side) by calling store.delete;
 *
 * first delete child (Answer) 1-by-1 then delete parent (Question)
 *
 * SQL1 : delete from answer_table where id=?
 * SQL2 : delete from answer_table where id=?
 * SQL3 : delete from question_table where id=?
 * 
 */
@Slf4j
@Order(1006)
@Component("delete-quest-1006")
public class DeleteQuestion1Runner implements CommandLineRunner {

    @Autowired
    private QAService QAService;
    @Autowired
    private QuestionStore questionStore;
    @Autowired
    private AnswerStore answerStore;

    @Override
    public void run(String... args) throws Exception {
        log.info("delete Question begin ====>");

        Optional<Question> questionOptional = questionStore.findByQname("What is Java?");
        if(questionOptional.isPresent()) {
            QAService.deleteQuestion(questionOptional.get());  // first delete child (Answer) 1-by-1 then delete parent (Question)
        }

        log.info("delete Question end <====  \n");
        log.info("=============================================>");
        List<Question> all = QAService.queryQuestion();

        all.stream()
                .peek(q->log.info("Question --> {}", q.toString()))
                .flatMap(q->q.getAnswers().stream())
                .forEach(a->log.info("Answer --> {}", a.toString()));
        log.info("runner Question Query end <====  \n");
    }
}
