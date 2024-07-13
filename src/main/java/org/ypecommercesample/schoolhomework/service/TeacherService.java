package org.ypecommercesample.schoolhomework.service;

import org.ypecommercesample.schoolhomework.dto.TeacherDto;
import org.ypecommercesample.schoolhomework.entity.Teacher;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    TeacherDto createTeacher(TeacherDto teacherDto);
    TeacherDto getTeacherById(UUID id);
    List<TeacherDto> getAllTeachers();
    TeacherDto updateTeacher(UUID id, TeacherDto teacherDto);
    void deleteTeacher(UUID id);

}