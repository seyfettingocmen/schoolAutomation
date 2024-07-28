package org.ypecommercesample.schoolhomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ypecommercesample.schoolhomework.dto.StudentLessonDto;
import org.ypecommercesample.schoolhomework.mapper.StudentLessonMapper;
import org.ypecommercesample.schoolhomework.request.StudentLessonRequest;
import org.ypecommercesample.schoolhomework.response.StudentLessonResponse;
import org.ypecommercesample.schoolhomework.service.StudentLessonService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("student-lessons")
public class StudentLessonController {
    private final StudentLessonService studentLessonService;
    private final StudentLessonMapper studentLessonMapper;

    @Autowired
    public StudentLessonController(StudentLessonService studentLessonService, StudentLessonMapper studentLessonMapper) {
        this.studentLessonService = studentLessonService;
        this.studentLessonMapper = studentLessonMapper;
    }

    @PostMapping
    public ResponseEntity<StudentLessonResponse> createStudentLesson(@RequestBody StudentLessonRequest request) {
        StudentLessonDto dto = studentLessonMapper.requestToDto(request);
        StudentLessonDto createdDto = studentLessonService.createStudentLesson(dto);
        StudentLessonResponse response = studentLessonMapper.dtoToResponse(createdDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentLessonResponse> getStudentLessonById(@PathVariable UUID id) {
        StudentLessonDto dto = studentLessonService.getStudentLessonById(id);
        StudentLessonResponse response = studentLessonMapper.dtoToResponse(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentLessonResponse>> getAllStudentLessons() {
        List<StudentLessonDto> dtos = studentLessonService.getAllStudentLessons();
        List<StudentLessonResponse> responses = dtos.stream()
                .map(studentLessonMapper::dtoToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentLessonResponse> updateStudentLesson(@PathVariable UUID id, @RequestBody StudentLessonRequest request) {
        StudentLessonDto dto = studentLessonMapper.requestToDto(request);
        StudentLessonDto updatedDto = studentLessonService.updateStudentLesson(id, dto);
        StudentLessonResponse response = studentLessonMapper.dtoToResponse(updatedDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentLesson(@PathVariable UUID id) {
        studentLessonService.deleteStudentLesson(id);
        return ResponseEntity.noContent().build();
    }
}
