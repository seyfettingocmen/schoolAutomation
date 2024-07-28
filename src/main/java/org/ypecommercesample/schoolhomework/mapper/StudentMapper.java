package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.dto.StudentDto;
import org.ypecommercesample.schoolhomework.dto.StudentLessonDto;
import org.ypecommercesample.schoolhomework.entity.Student;
import org.ypecommercesample.schoolhomework.entity.StudentLesson;
import org.ypecommercesample.schoolhomework.request.StudentRequest;
import org.ypecommercesample.schoolhomework.response.StudentResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    private final StudentLessonMapper studentLessonMapper;

    public StudentMapper(StudentLessonMapper studentLessonMapper) {
        this.studentLessonMapper = studentLessonMapper;
    }

    public StudentResponse dtoToResponse(StudentDto studentDto) {
        return StudentResponse.builder()
                .uuid(studentDto.getUuid())
                .fullName(studentDto.getFullName())
                .age(studentDto.getAge())
                .tckn(studentDto.getTckn())
                .studentLessons(studentDto.getStudentLessons())
                .build();
    }

    public StudentDto requestToDto(StudentRequest studentRequest) {
        return StudentDto.builder()
                .age(studentRequest.getAge())
                .tckn(studentRequest.getTckn())
                .fullName(studentRequest.getFullName())
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

        if (studentDto.getStudentLessons() != null) {
            List<StudentLesson> studentLessons = studentDto.getStudentLessons().stream()
                    .map(studentLessonMapper::dtoToEntity)
                    .collect(Collectors.toList());
            student.setStudentLessons(studentLessons);

            // Her StudentLesson nesnesinin student referansını ayarla
            studentLessons.forEach(sl -> sl.setStudent(student));
        }

        return student;
    }

    public StudentDto entityToDto(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        List<StudentLessonDto> studentLessonDtos = student.getStudentLessons().stream()
                .map(studentLessonMapper::entityToDto)
                .collect(Collectors.toList());

        return StudentDto.builder()
                .uuid(student.getId())
                .age(student.getAge())
                .tckn(student.getTckn())
                .fullName(student.getFullName())
                .studentLessons(studentLessonDtos)
                .build();
    }
}