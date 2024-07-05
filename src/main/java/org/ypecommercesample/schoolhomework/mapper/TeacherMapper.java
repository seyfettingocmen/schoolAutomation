package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.LessonDto;
import org.ypecommercesample.schoolhomework.dto.TeacherDto;
import org.ypecommercesample.schoolhomework.entity.Teacher;
import org.ypecommercesample.schoolhomework.request.TeacherRequest;
import org.ypecommercesample.schoolhomework.response.TeacherResponse;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.service.LessonService;

import java.util.List;
import java.util.stream.Collectors;

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
        return TeacherDto.builder()
                .id(teacher.getId())
                .fullName(teacher.getFullName())
                .age(teacher.getAge())
                .tckn(teacher.getTckn())
                .lessonDto(lessonMapper.entityToDto(teacher.getLesson()))
                .build();
    }

    public Teacher dtoToEntity(TeacherDto teacherDto) {
        if (teacherDto == null) {
            throw new IllegalArgumentException("TeacherDto cannot be null");
        }

        Teacher entity = new Teacher();
        entity.setId(teacherDto.getId());
        entity.setFullName(teacherDto.getFullName());
        entity.setAge(teacherDto.getAge());
        entity.setTckn(teacherDto.getTckn());

        if (teacherDto.getLessonDto() != null) {
            entity.setLesson(lessonMapper.dtoToEntity(teacherDto.getLessonDto()));
        }

        return entity;
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
                .lessonDto(teacherDto.getLessonDto() != null
                        ? lessonMapper.entityToDto(lessonService.findByLessonId(teacherDto.getLessonDto().getId()))
                        : null)
                .build();
    }

}