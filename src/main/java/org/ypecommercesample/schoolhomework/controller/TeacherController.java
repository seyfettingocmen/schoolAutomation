package org.ypecommercesample.schoolhomework.controller;

import org.ypecommercesample.schoolhomework.dto.TeacherDto;
import org.ypecommercesample.schoolhomework.request.TeacherRequest;
import org.ypecommercesample.schoolhomework.response.TeacherResponse;
import org.ypecommercesample.schoolhomework.service.TeacherService;
import org.ypecommercesample.schoolhomework.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherMapper teacherMapper;


    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody TeacherRequest teacherRequest) {
        TeacherDto teacherDto = teacherMapper.requestToDto(teacherRequest);
        teacherDto = teacherService.createTeacher(teacherDto);
        TeacherResponse teacherResponse = teacherMapper.dtoToResponse(teacherDto);
        return ResponseEntity.ok(teacherResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable UUID id) {
        TeacherDto teacherDto = teacherService.getTeacherById(id);
        TeacherResponse teacherResponse = teacherMapper.dtoToResponse(teacherDto);
        return ResponseEntity.ok(teacherResponse);
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
        List<TeacherDto> teacherDtos = teacherService.getAllTeachers();
        List<TeacherResponse> teacherResponses = teacherDtos.stream().map(teacherMapper::dtoToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(teacherResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> updateTeacher(@PathVariable UUID id, @RequestBody TeacherRequest teacherRequest) {
        TeacherDto teacherDto = teacherMapper.requestToDto(teacherRequest);
        teacherDto = teacherService.updateTeacher(id, teacherDto);
        TeacherResponse teacherResponse = teacherMapper.dtoToResponse(teacherDto);
        return ResponseEntity.ok(teacherResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable UUID id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}