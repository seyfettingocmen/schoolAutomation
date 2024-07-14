package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.dto.LessonDto;
import org.ypecommercesample.schoolhomework.entity.ClassBranch;
import org.ypecommercesample.schoolhomework.entity.Lesson;
import org.ypecommercesample.schoolhomework.entity.Teacher;
import org.ypecommercesample.schoolhomework.request.LessonRequest;
import org.ypecommercesample.schoolhomework.response.LessonResponse;
import org.ypecommercesample.schoolhomework.service.impl.ClassBranchServiceImpl;
import org.ypecommercesample.schoolhomework.service.impl.TeacherServiceImpl;

import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class LessonMapper {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassBranchServiceImpl classBranchService;

    public LessonResponse dtoToResponse(LessonDto lessonDto) {
        Teacher teacher = lessonDto.getTeacherId() != null ?
                teacherService.findTeacherById(lessonDto.getTeacherId()) : null;
        ClassBranch classBranch = lessonDto.getClassBranchId() != null ?
                classBranchService.findClassBranchById(lessonDto.getClassBranchId()) : null;

        if (teacher == null && classBranch == null) {
            // Both teacher and classBranch are null, potentially return null response
            return null;
        }

        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lessonDto.getId());
        lessonResponse.setName(lessonDto.getName());
        lessonResponse.setTeacherId(teacher != null ? teacher.getId() : null);
        lessonResponse.setStudentDtoList(lessonDto.getStudentDtoList());
        lessonResponse.setClassBranchId(classBranch != null ? classBranch.getId() : null); // Set classBranchId only if classBranch is not null

        return lessonResponse;
    }



    public LessonDto requestToDto(LessonRequest lessonRequest) {
        LessonDto lessonDto = new LessonDto();
        ClassBranch classBranch = classBranchService.findClassBranchById(lessonRequest.getClassBranchId());
        lessonDto.setName(lessonRequest.getName());
        lessonDto.setClassBranchId(classBranch.getId());

        return lessonDto;
    }

    public LessonDto entityToDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto();

        lessonDto.setId(lesson.getId());
        lessonDto.setName(lesson.getName());

        // Handle null student list by setting an empty list
        lessonDto.setStudentDtoList(lesson.getStudentList() != null ?
                lesson.getStudentList().stream()
                        .map(studentMapper::entityToDto)
                        .collect(Collectors.toList()) : Collections.emptyList());

        // Handle null teacher or teacherId by setting a default teacherId
        UUID teacherId = lesson.getTeacher() != null ? lesson.getTeacher().getId() : null;
        lessonDto.setTeacherId(teacherId);

        lessonDto.setClassBranchId(lesson.getClassBranch().getId());
        return lessonDto;
    }



    public Lesson dtoToEntity(LessonDto lessonDto) {
        Lesson.LessonBuilder builder = Lesson.builder()
                .id(lessonDto.getId())
                .name(lessonDto.getName());

        // Null kontrolleri eklendi
        if (lessonDto.getTeacherId() != null) {
            builder.teacher(teacherService.findTeacherById(lessonDto.getTeacherId()));
        }

        if (lessonDto.getClassBranchId() != null) {
            builder.classBranch(classBranchService.findClassBranchById(lessonDto.getClassBranchId()));
        }

        // Student listesi için kontrol eklendi (Optional kullanımı değerlendirilebilir)
        if (lessonDto.getStudentDtoList() != null) {
            builder.studentList(lessonDto.getStudentDtoList().stream()
                    .map(studentMapper::dtoToEntity)
                    .collect(Collectors.toList()));
        }

        return builder.build();
    }
}
