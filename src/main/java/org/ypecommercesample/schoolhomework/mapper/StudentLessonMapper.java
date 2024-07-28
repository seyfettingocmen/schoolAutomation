package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.dto.StudentLessonDto;
import org.ypecommercesample.schoolhomework.entity.Lesson;
import org.ypecommercesample.schoolhomework.entity.Student;
import org.ypecommercesample.schoolhomework.entity.StudentLesson;
import org.ypecommercesample.schoolhomework.request.StudentLessonRequest;
import org.ypecommercesample.schoolhomework.response.StudentLessonResponse;
@Component
public class StudentLessonMapper {
    public StudentLessonResponse dtoToResponse(StudentLessonDto studentLessonDto) {
        return StudentLessonResponse.builder()
                .id(studentLessonDto.getId())
                .studentId(studentLessonDto.getStudentId())
                .lessonId(studentLessonDto.getLessonId())
                .grade(studentLessonDto.getGrade())
                .semester(studentLessonDto.getSemester())
                .build();
    }

    public StudentLessonDto requestToDto(StudentLessonRequest studentLessonRequest) {
        return StudentLessonDto.builder()
                .studentId(studentLessonRequest.getStudentId())
                .lessonId(studentLessonRequest.getLessonId())
                .grade(studentLessonRequest.getGrade())
                .semester(studentLessonRequest.getSemester())
                .build();
    }

    public StudentLesson dtoToEntity(StudentLessonDto studentLessonDto) {
        return StudentLesson.builder()
                .id(studentLessonDto.getId())
                .student(Student.builder().id(studentLessonDto.getStudentId()).build())
                .lesson(Lesson.builder().id(studentLessonDto.getLessonId()).build())
                .grade(studentLessonDto.getGrade())
                .semester(studentLessonDto.getSemester())
                .build();
    }

    public StudentLessonDto entityToDto(StudentLesson studentLesson) {
        return StudentLessonDto.builder()
                .id(studentLesson.getId())
                .studentId(studentLesson.getStudent().getId())
                .lessonId(studentLesson.getLesson().getId())
                .grade(studentLesson.getGrade())
                .semester(studentLesson.getSemester())
                .build();
    }
}
