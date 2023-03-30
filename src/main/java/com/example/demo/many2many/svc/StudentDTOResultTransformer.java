package com.example.demo.many2many.svc;

import com.example.demo.many2many.data.CourseDTO;
import com.example.demo.many2many.data.StudentDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class StudentDTOResultTransformer implements org.hibernate.transform.ResultTransformer {
    private final static int INDEX_AGE = 0;
    private final static int INDEX_TITLE = 1;
    private final static int INDEX_NAME = 2;
    private Map<String, StudentDTO> data = new LinkedHashMap<>();
    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Objects.requireNonNull(tuple[INDEX_TITLE]);
        StudentDTO studentDTO = data.computeIfAbsent((String) tuple[INDEX_TITLE], x->new StudentDTO((Integer) tuple[INDEX_AGE], (String) tuple[INDEX_TITLE]));
        studentDTO.addCourse(new CourseDTO((String) tuple[INDEX_NAME]));
        return studentDTO;
    }

    @Override
    public List transformList(List collection) {
        ArrayList arr =  new ArrayList(data.values());
        return arr;
    }
}
