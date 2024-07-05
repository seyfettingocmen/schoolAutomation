package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.StudentDto;
import org.ypecommercesample.schoolhomework.entity.Student;
import org.ypecommercesample.schoolhomework.request.StudentRequest;
import org.ypecommercesample.schoolhomework.response.StudentResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    @Autowired
    ClassBranchMapper classBranchMapper;

    @Autowired
    LessonMapper lessonMapper;

    public StudentResponse dtoToResponse(StudentDto studentDto) {
        return StudentResponse.builder()
                .uuid(studentDto.getUuid())
                .fullName(studentDto.getFullName())
                .age(studentDto.getAge())
                .tckn(studentDto.getTckn())
                .lessonDtoList(studentDto.getLessonDtoList())
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

        return StudentDto.builder()
                .uuid(student.getUuid())
                .age(student.getAge())
                .tckn(student.getTckn())
                .fullName(student.getFullName())
                .uuid(student.getUuid())
                .lessonDtoList(student.getLessons().stream().map(lessonMapper::entityToDto).collect(Collectors.toList()))
                .build();
    }

    public Student dtoToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setUuid(studentDto.getUuid());
        student.setFullName(studentDto.getFullName());
        student.setAge(studentDto.getAge());
        student.setTckn(studentDto.getTckn());
        student.setLessons(studentDto.getLessonDtoList() != null ?
                studentDto.getLessonDtoList().stream().map(lessonMapper::dtoToEntity).collect(Collectors.toList()) :
                new ArrayList<>());
        return student;
    }
}