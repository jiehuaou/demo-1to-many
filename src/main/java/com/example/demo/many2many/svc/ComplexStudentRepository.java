package com.example.demo.many2many.svc;

import com.example.demo.many2many.data.StudentDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * define custom interface : return complex DTO with nest DTO
 */
public interface ComplexStudentRepository {
    // projection with Complex DTO object,
    public List<StudentDTO> findComplexStudentDTO();
}
