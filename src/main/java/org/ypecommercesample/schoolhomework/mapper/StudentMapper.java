package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.StudentDto;
import org.ypecommercesample.schoolhomework.entity.Student;
import org.ypecommercesample.schoolhomework.request.StudentRequest;
import org.ypecommercesample.schoolhomework.response.StudentResponse;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.service.LessonService;

@Component
public class StudentMapper {

    @Autowired
    ClassBranchMapper classBranchMapper;

    @Autowired
    LessonMapper lessonMapper;

    @Autowired
    LessonService lessonService;

    public StudentResponse dtoToResponse(StudentDto studentDto) {
        return StudentResponse.builder()
                .uuid(studentDto.getUuid())
                .fullName(studentDto.getFullName())
                .age(studentDto.getAge())
                .tckn(studentDto.getTckn())
                .lessonId(studentDto.getLessonId())
                .build();
    }

    public StudentDto requestToDto(StudentRequest studentRequest) {
        return StudentDto.builder()
                .age(studentRequest.getAge())
                .tckn(studentRequest.getTckn())
                .fullName(studentRequest.getFullName())

                .build();

    }

    public StudentDto entityToDto(Student student) {
        if (student == null) {
            return null;
        }

        return StudentDto.builder()
                .uuid(student.getId())
                .age(student.getAge())
                .tckn(student.getTckn())
                .fullName(student.getFullName())
                .lessonId(student.getLesson() != null ? student.getLesson().getId() : null) // Null kontrolü ekleyin
                .build();
    }

    public Student dtoToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getUuid());
        student.setFullName(studentDto.getFullName());
        student.setAge(studentDto.getAge());
        student.setTckn(studentDto.getTckn());
        student.getLesson().setId(studentDto.getLessonId());
        return student;
    }
}