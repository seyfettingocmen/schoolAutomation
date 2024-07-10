package org.ypecommercesample.schoolhomework.service;

import org.ypecommercesample.schoolhomework.dto.LessonDto;
import org.ypecommercesample.schoolhomework.entity.Lesson;

import java.util.List;
import java.util.UUID;

public interface LessonService {
    LessonDto createLesson(LessonDto lessonDto);
    LessonDto getLessonById(UUID id);
    List<LessonDto> getAllLessons();
    LessonDto updateLesson(UUID id, LessonDto lessonDto);
    void deleteLesson(UUID id);


}