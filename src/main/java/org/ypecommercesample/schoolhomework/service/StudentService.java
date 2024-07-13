package org.ypecommercesample.schoolhomework.service;

import org.ypecommercesample.schoolhomework.dto.StudentDto;
import org.ypecommercesample.schoolhomework.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentById(UUID id);
    List<StudentDto> getAllStudents();
    StudentDto updateStudent(UUID id, StudentDto studentDto);
    void deleteStudent(UUID id);

}