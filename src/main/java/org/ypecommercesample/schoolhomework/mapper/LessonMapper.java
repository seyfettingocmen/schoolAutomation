package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.dto.ClassBranchDto;
import org.ypecommercesample.schoolhomework.dto.LessonDto;
import org.ypecommercesample.schoolhomework.entity.Lesson;
import org.ypecommercesample.schoolhomework.request.LessonRequest;
import org.ypecommercesample.schoolhomework.response.LessonResponse;
import org.ypecommercesample.schoolhomework.service.*;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class LessonMapper {
    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    LessonService lessonService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ClassBranchMapper classBranchMapper;

    @Autowired
    ClassBranchService classBranchService;

    // LessonDto nesnesini LessonResponse nesnesine dönüştürür
    public LessonResponse dtoToResponse(LessonDto lessonDto) {
        return LessonResponse.builder()
                .id(lessonDto.getId())
                .name(lessonDto.getName())
                .teacherId(lessonDto.getTeacherId())
                .classBranchDto(lessonDto.getClassBranchDto())
                .studentDtoList(lessonDto.getStudentDtoList())
                .build();
    }

    // LessonRequest nesnesini LessonDto nesnesine dönüştürür
    public LessonDto requestToDto(LessonRequest lessonRequest) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setName(lessonRequest.getName());

        // Null kontrolü ve yeni ClassBranchDto oluşturma
        if (lessonRequest.getClassBranchId() != null) {
            ClassBranchDto classBranchDto = new ClassBranchDto();
            classBranchDto.setId(lessonRequest.getClassBranchId());
            lessonDto.setClassBranchDto(classBranchDto);
        }
        return lessonDto;
    }

    // Lesson nesnesini LessonDto nesnesine dönüştürür
    public LessonDto entityToDto(Lesson lesson) {
        if (lesson == null) {
            return null;
        }

        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(lesson.getId()); // ID'yi ekledim
        lessonDto.setName(lesson.getName());

        // Null kontrolü ekleyin
        if (lesson.getStudentList() != null) {
            lessonDto.setStudentDtoList(lesson.getStudentList().stream()
                    .map(studentMapper::entityToDto)
                    .collect(Collectors.toList()));
        } else {
            lessonDto.setStudentDtoList(Collections.emptyList());
        }

        // Null kontrolü ekleyin
        if (lesson.getTeacher() != null) {
            lessonDto.setTeacherId(lesson.getTeacher().getId()); // Geliştirilmiş null kontrolü
        }

        // Null kontrolü ekleyin
        if (lesson.getClassBranch() != null) {
            lessonDto.setClassBranchDto(classBranchMapper.entityToDto(lesson.getClassBranch()));
        }

        return lessonDto;
    }

    // LessonDto nesnesini Lesson nesnesine dönüştürür
    public Lesson dtoToEntity(LessonDto lessonDto) {
        Lesson.LessonBuilder builder = Lesson.builder()
                .id(lessonDto.getId())
                .name(lessonDto.getName());

        // Null kontrolü ekleyin
        if (lessonDto.getTeacherId() != null) {
            builder.teacher(teacherService.findTeacherById(lessonDto.getTeacherId()));
        }

        if (lessonDto.getClassBranchDto() != null) {
            builder.classBranch(classBranchService.findClassBranchById(lessonDto.getClassBranchDto().getId()));
        }

        if (lessonDto.getStudentDtoList() != null) {
            builder.studentList(lessonDto.getStudentDtoList().stream()
                    .map(studentMapper::dtoToEntity)
                    .collect(Collectors.toList()));
        }

        return builder.build();
    }
}