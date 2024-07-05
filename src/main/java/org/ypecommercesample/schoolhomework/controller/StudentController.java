package org.ypecommercesample.schoolhomework.controller;

import org.ypecommercesample.schoolhomework.dto.StudentDto;
import org.ypecommercesample.schoolhomework.request.StudentRequest;
import org.ypecommercesample.schoolhomework.response.StudentResponse;
import org.ypecommercesample.schoolhomework.service.StudentService;
import org.ypecommercesample.schoolhomework.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest studentRequest) {
        StudentDto studentDto = studentMapper.requestToDto(studentRequest);
        studentDto = studentService.createStudent(studentDto);
        StudentResponse studentResponse = studentMapper.dtoToResponse(studentDto);
        return ResponseEntity.ok(studentResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable UUID id) {
        StudentDto studentDto = studentService.getStudentById(id);
        StudentResponse studentResponse = studentMapper.dtoToResponse(studentDto);
        return ResponseEntity.ok(studentResponse);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentDto> studentDtos = studentService.getAllStudents();
        List<StudentResponse> studentResponses = studentDtos.stream().map(studentMapper::dtoToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(studentResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable UUID id, @RequestBody StudentRequest studentRequest) {
        StudentDto studentDto = studentMapper.requestToDto(studentRequest);
        studentDto = studentService.updateStudent(id, studentDto);
        StudentResponse studentResponse = studentMapper.dtoToResponse(studentDto);
        return ResponseEntity.ok(studentResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}