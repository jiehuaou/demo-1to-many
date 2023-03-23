package com.example.demo.one2many.svc;

import com.example.demo.one2many.data.Answer;
import com.example.demo.one2many.data.Question;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class QAService {
    private final QuestionStore questionStore;
    private final AnswerStore answerStore;

    public QAService(QuestionStore myStore, AnswerStore answerStore) {
        this.questionStore = myStore;
        this.answerStore = answerStore;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int save(Question question) {
        return questionStore.save(question).getId();
    }

    public List<Question> queryQuestion() {
        return questionStore.findAll();
    }

    public List<Answer> queryAnswer() {
        return answerStore.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteQuestion(Question question) {
        questionStore.delete(question);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAnswer(Answer answer) {
        answerStore.delete(answer);
    }

}
