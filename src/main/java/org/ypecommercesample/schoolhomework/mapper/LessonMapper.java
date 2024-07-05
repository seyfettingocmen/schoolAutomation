package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.ClassBranchDto;
import org.ypecommercesample.schoolhomework.dto.LessonDto;
import org.ypecommercesample.schoolhomework.entity.Lesson;
import org.ypecommercesample.schoolhomework.request.LessonRequest;
import org.ypecommercesample.schoolhomework.response.LessonResponse;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.service.ClassBranchService;
import org.ypecommercesample.schoolhomework.service.LessonService;
import org.ypecommercesample.schoolhomework.service.StudentService;
import org.ypecommercesample.schoolhomework.service.TeacherService;

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

    public LessonResponse dtoToResponse(LessonDto dto) {
        return LessonResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .teacherDto(dto.getTeacherDto())
                .classBranchDto(dto.getClassBranchDto())
                .studentDtoList(dto.getStudentDtoList())
                .build();
    }

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

    public LessonDto entityToDto(Lesson lesson) {
        if (lesson == null) {
            return null; // veya uygun bir şekilde handle edebilirsiniz.
        }
        LessonDto lessonDto = new LessonDto();
        lessonDto.setName(lesson.getName());
        lessonDto.setStudentDtoList(lesson.getStudentList().stream().map(studentMapper::entityToDto).collect(Collectors.toList()));

        // Null kontrolü ekleyin
        if (lesson.getTeacher() != null) {
            lessonDto.setTeacherDto(teacherMapper.entityToDto(teacherService.findTeacherById(lesson.getTeacher().getId())));
        }


        if (lesson.getClassBranch() != null) {
            lessonDto.setClassBranchDto(classBranchMapper.entityToDto(classBranchService.findClassBranchById(lesson.getClassBranch().getId())));
        }

        return lessonDto;
    }

    public Lesson dtoToEntity(LessonDto lessonDto) {
        Lesson.LessonBuilder builder = Lesson.builder()
                .id(lessonDto.getId())
                .name(lessonDto.getName());

        // Null kontrolü ekleyin
        if (lessonDto.getTeacherDto() != null) {
            builder.teacher(teacherService.findTeacherById(lessonDto.getTeacherDto().getId()));
        }

        if (lessonDto.getClassBranchDto() != null) {
            builder.classBranch(classBranchService.findClassBranchById(lessonDto.getClassBranchDto().getId()));
        }

        if (lessonDto.getStudentDtoList() != null) {
            builder.student(studentService.findById(lessonDto.getStudentDtoList().getUuid()));
        }

        return builder.build();
    }
}