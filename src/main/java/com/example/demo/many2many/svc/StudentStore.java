package com.example.demo.many2many.svc;

import com.example.demo.many2many.data.Student;
import com.example.demo.many2many.data.StudentDTO;
import com.example.demo.many2many.data.StudentView;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface StudentStore extends JpaRepository<Student, Integer> {

    // specify which association will be fetched in this query
    @EntityGraph(attributePaths = "likedCourses")
    public List<Student> findByTitle(String name);

    // projection with Interface
    @Query("select s.age as age, s.title as title from Student s")
    public List<StudentView> findAllView();

    // projection with DTO object
    @Query("select new com.example.demo.many2many.data.StudentDTO(s.age, s.title) from Student s")
    public List<StudentDTO> findAllDto();

    //List<Student> findAll();
}
