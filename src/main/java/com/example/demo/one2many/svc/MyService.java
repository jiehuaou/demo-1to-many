package com.example.demo.one2many.svc;

import com.example.demo.one2many.data.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MyService {
    private final MyStore myStore;

    public MyService(MyStore myStore) {
        this.myStore = myStore;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int create(Question question) {
        return myStore.save(question).getId();
    }

}
