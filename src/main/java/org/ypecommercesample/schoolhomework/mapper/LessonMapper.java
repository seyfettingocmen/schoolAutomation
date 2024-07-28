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
    private StudentLessonMapper studentLessonMapper;

    @Autowired
    private ClassBranchServiceImpl classBranchService;

    public LessonResponse dtoToResponse(LessonDto lessonDto) {
        Teacher teacher = lessonDto.getTeacherId() != null ?
                teacherService.findTeacherById(lessonDto.getTeacherId()) : null;
        ClassBranch classBranch = lessonDto.getClassBranchId() != null ?
                classBranchService.findClassBranchById(lessonDto.getClassBranchId()) : null;

        if (teacher == null && classBranch == null) {
            return null;
        }

        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lessonDto.getId());
        lessonResponse.setName(lessonDto.getName());
        lessonResponse.setTeacherId(teacher != null ? teacher.getId() : null);
        lessonResponse.setStudentLessonDtoList(lessonDto.getStudentLessonDtoList());
        lessonResponse.setClassBranchId(classBranch != null ? classBranch.getId() : null);

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

        lessonDto.setStudentLessonDtoList(lesson.getStudentLessons() != null ?
                lesson.getStudentLessons().stream()
                        .map(studentLessonMapper::entityToDto)
                        .collect(Collectors.toList()) : Collections.emptyList());

        UUID teacherId = lesson.getTeacher() != null ? lesson.getTeacher().getId() : null;
        lessonDto.setTeacherId(teacherId);

        lessonDto.setClassBranchId(lesson.getClassBranch().getId());
        return lessonDto;
    }

    public Lesson dtoToEntity(LessonDto lessonDto) {
        Lesson.LessonBuilder builder = Lesson.builder()
                .id(lessonDto.getId())
                .name(lessonDto.getName());

        if (lessonDto.getTeacherId() != null) {
            builder.teacher(teacherService.findTeacherById(lessonDto.getTeacherId()));
        }

        if (lessonDto.getClassBranchId() != null) {
            builder.classBranch(classBranchService.findClassBranchById(lessonDto.getClassBranchId()));
        }

        if (lessonDto.getStudentLessonDtoList() != null) {
            builder.studentLessons(lessonDto.getStudentLessonDtoList().stream()
                    .map(studentLessonMapper::dtoToEntity)
                    .collect(Collectors.toList()));
        }

        return builder.build();
    }
}
