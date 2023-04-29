package com.example.demo.m2m.simple.svc;

import com.example.demo.m2m.simple.data.StudentDTO;

import java.util.List;

/**
 * define custom interface : return complex DTO with nest DTO
 */
public interface ComplexStudentRepository {
    // projection with Complex DTO object,
    List<StudentDTO> findComplexStudentDTO();

    List<StudentDTO> findComplexStudentDTO2();
}
