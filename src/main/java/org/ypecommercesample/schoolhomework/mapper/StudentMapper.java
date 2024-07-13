package org.ypecommercesample.schoolhomework.mapper;

import org.ypecommercesample.schoolhomework.dto.StudentDto;
import org.ypecommercesample.schoolhomework.entity.Lesson;
import org.ypecommercesample.schoolhomework.entity.Student;
import org.ypecommercesample.schoolhomework.request.StudentRequest;
import org.ypecommercesample.schoolhomework.response.StudentResponse;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.service.impl.LessonServiceImpl;

@Component
public class StudentMapper {

    private final LessonServiceImpl lessonService;

    public StudentMapper(LessonServiceImpl lessonService) {
        this.lessonService = lessonService;
    }

    public StudentResponse dtoToResponse(StudentDto studentDto) {
        Lesson lesson = lessonService.findByLessonId(studentDto.getLessonId());
        return StudentResponse.builder()
                .uuid(studentDto.getUuid())
                .fullName(studentDto.getFullName())
                .age(studentDto.getAge())
                .tckn(studentDto.getTckn())
                .lessonId(lesson.getId())
                .build();
    }

    public StudentDto requestToDto(StudentRequest studentRequest) {
        Lesson lesson = lessonService.findByLessonId(studentRequest.getLessonId());
        return StudentDto.builder()
                .age(studentRequest.getAge())
                .tckn(studentRequest.getTckn())
                .fullName(studentRequest.getFullName())
                .lessonId(lesson.getId())

                .build();

    }
    public Student dtoToEntity(StudentDto studentDto) {
        if (studentDto == null) {
            throw new IllegalArgumentException("StudentDto cannot be null");
        }

        Student student = new Student();
        student.setId(studentDto.getUuid());
        student.setFullName(studentDto.getFullName());
        student.setAge(studentDto.getAge());
        student.setTckn(studentDto.getTckn());
        if (studentDto.getLessonId() != null) {
            Lesson lesson = lessonService.findByLessonId(studentDto.getLessonId());
            student.setLesson(lesson);
        }
        return student;
    }

    public StudentDto entityToDto(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        return StudentDto.builder()
                .uuid(student.getId())
                .age(student.getAge())
                .tckn(student.getTckn())
                .fullName(student.getFullName())
                .lessonId(student.getLesson() != null ? student.getLesson().getId() : null) // Null kontrolü ekleyin
                .build();
    }


}