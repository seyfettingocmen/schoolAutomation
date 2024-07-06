package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.dto.TeacherDto;
import org.ypecommercesample.schoolhomework.entity.Lesson;
import org.ypecommercesample.schoolhomework.entity.Teacher;
import org.ypecommercesample.schoolhomework.request.TeacherRequest;
import org.ypecommercesample.schoolhomework.response.TeacherResponse;
import org.ypecommercesample.schoolhomework.service.LessonService;

import java.util.UUID;

@Component
public class TeacherMapper {

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private LessonService lessonService;

    public TeacherDto requestToDto(TeacherRequest teacherRequest) {
        if (teacherRequest == null) {
            return null;
        }
        return TeacherDto.builder()
                .fullName(teacherRequest.getFullName())
                .age(teacherRequest.getAge())
                .tckn(teacherRequest.getTckn())
                .build();
    }

    public TeacherDto entityToDto(Teacher teacher) {
        if (teacher == null) {
            return null;
        }

        UUID lessonId = teacher.getLesson() != null ? teacher.getLesson().getId() : null;

        return TeacherDto.builder()
                .id(teacher.getId())
                .fullName(teacher.getFullName())
                .age(teacher.getAge())
                .tckn(teacher.getTckn())
                .lessonId(lessonId)
                .build();
    }

    public Teacher dtoToEntity(TeacherDto teacherDto) {
        if (teacherDto == null) {
            throw new IllegalArgumentException("TeacherDto cannot be null");
        }

        Teacher teacher = new Teacher();
        teacher.setId(teacherDto.getId());
        teacher.setFullName(teacherDto.getFullName());
        teacher.setAge(teacherDto.getAge());
        teacher.setTckn(teacherDto.getTckn());

        if (teacherDto.getLessonId() != null) {
            Lesson lesson = lessonService.findByLessonId(teacherDto.getLessonId());
            teacher.setLesson(lesson);
        }

        return teacher;
    }

    public TeacherResponse dtoToResponse(TeacherDto teacherDto) {
        if (teacherDto == null) {
            throw new IllegalArgumentException("TeacherDto cannot be null");
        }

        return TeacherResponse.builder()
                .id(teacherDto.getId())
                .fullName(teacherDto.getFullName())
                .age(teacherDto.getAge())
                .tckn(teacherDto.getTckn())
                .lessonId(teacherDto.getLessonId())
                .build();
    }
}