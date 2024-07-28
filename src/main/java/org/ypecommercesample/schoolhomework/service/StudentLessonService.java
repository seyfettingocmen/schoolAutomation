package org.ypecommercesample.schoolhomework.service;

import org.ypecommercesample.schoolhomework.dto.StudentLessonDto;

import java.util.List;
import java.util.UUID;

public interface StudentLessonService {
    StudentLessonDto createStudentLesson(StudentLessonDto studentLessonDto);
    StudentLessonDto getStudentLessonById(UUID id);
    List<StudentLessonDto> getAllStudentLessons();
    StudentLessonDto updateStudentLesson(UUID id, StudentLessonDto studentLessonDto);
    void deleteStudentLesson(UUID id);
    List<StudentLessonDto> getStudentLessonsByStudentId(UUID studentId);
    List<StudentLessonDto> getStudentLessonsByLessonId(UUID lessonId);
}
