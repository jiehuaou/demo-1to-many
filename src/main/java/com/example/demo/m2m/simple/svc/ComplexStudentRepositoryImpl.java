package com.example.demo.m2m.simple.svc;

import com.example.demo.m2m.simple.data.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * class name must have Impl postfix with the fragment interface
 */
@Component()
public class ComplexStudentRepositoryImpl implements ComplexStudentRepository{

    private final static String sql = "select a.age as age, a.title as title, b.name as name from student a " +
            "left outer join course_like c on a.id=c.student_id " +
            "left outer join course b on c.course_id=b.id ";
    //@PersistenceContext
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Override
    public List<StudentDTO> findComplexStudentDTO() {
        return entityManager.createNativeQuery(sql)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new StudentDTOResultTransformer())
                .getResultList();
    }

    @Override
    public List<StudentDTO> findComplexStudentDTO2() {
        List<StudentDTO> result = null;
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            result = entityManagerFactory.createEntityManager()
                    .createNativeQuery(sql)
                    .unwrap(org.hibernate.query.Query.class)
                    .setResultTransformer(new StudentDTOResultTransformer())
                    .getResultList();
        } finally {
            manager.close();
        }
        return result;
    }
}
