package com.example.demo.m2m.simple.svc;

import com.example.demo.m2m.simple.data.Student;
import com.example.demo.m2m.simple.data.StudentDTO;
import com.example.demo.m2m.simple.data.StudentView;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public interface StudentStore extends JpaRepository<Student, Long>, ComplexStudentRepository {

    // specify which association will be fetched in this query
    @EntityGraph(attributePaths = "likedCourses")
    public Student findTop1ByTitle(String name);

    @EntityGraph(attributePaths = "likedCourses")
    public Optional<Student> findFirstByTitle(String name);

    @EntityGraph(attributePaths = "likedCourses")
    public List<Student> findByTitle(String name);

    // projection with Interface, performance is slower than DTO
    @Query("select s.age as age, s.title as title from Student s")
    public List<StudentView> findAllView();

    // projection with simple DTO object, performance is better than Interface projection
    @Query("select new com.example.demo.m2m.simple.data.StudentDTO(s.age, s.title) from Student s")
    public List<StudentDTO> findAllStudentDTO();

    // returning Entity Stream, it must be executed within @Transaction
    @EntityGraph(attributePaths = "likedCourses")
    @Query("select s from Student s")
    Stream<Student> findAllAsStream();
}
