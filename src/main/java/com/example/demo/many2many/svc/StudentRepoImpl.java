package com.example.demo.many2many.svc;

import com.example.demo.many2many.data.StudentDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class StudentRepoImpl implements StudentRepo{

    private final String sql = "select a.age as age, a.title as title, b.name as name from student a " +
            "left outer join course_like c on a.id=c.student_id " +
            "left outer join course b on c.course_id=b.id ";
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<StudentDTO> findComplexStudentDTO() {
        return entityManager.createNativeQuery(sql)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new StudentDTOResultTransformer())
                .getResultList();
    }
}
