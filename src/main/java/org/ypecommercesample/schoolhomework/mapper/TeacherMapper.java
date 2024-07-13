package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.dto.TeacherDto;
import org.ypecommercesample.schoolhomework.entity.Lesson;
import org.ypecommercesample.schoolhomework.entity.Teacher;
import org.ypecommercesample.schoolhomework.request.TeacherRequest;
import org.ypecommercesample.schoolhomework.response.TeacherResponse;
import org.ypecommercesample.schoolhomework.service.impl.LessonServiceImpl;

import java.util.UUID;

@Component
public class TeacherMapper {

    @Autowired
    private LessonServiceImpl lessonService;

    public TeacherDto requestToDto(TeacherRequest teacherRequest) {
        Lesson lesson = lessonService.findByLessonId(teacherRequest.getLessonId());
        return TeacherDto.builder()
                .fullName(teacherRequest.getFullName())
                .age(teacherRequest.getAge())
                .tckn(teacherRequest.getTckn())
                .lessonId(lesson.getId())
                .build();
    }

    public TeacherDto entityToDto(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        // 1. Seçenek: Eager Fetching için (Teacher entity'sinde)
        // Teacher sınıfında lesson alanına @Fetch(FetchMode.EAGER) ekleyebilirsiniz.

        // 2. Seçenek: Join Fetch için (repository.findById çağrısında)
        // Optional<Teacher> teacher = repository.findById(id, FetchMode.JOIN);
        Lesson lesson = teacher.getLesson();
        UUID lessonId = lesson != null ? lesson.getId() : null;

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
        Lesson lesson = lessonService.findByLessonId(teacherDto.getLessonId());
        // teacherDto.getLessonId() null olup olmadığını kontrol edin
        if (teacherDto.getLessonId() == null) {
            throw new IllegalArgumentException("Lesson ID must not be null");
        }
        return TeacherResponse.builder()
                .id(teacherDto.getId())
                .fullName(teacherDto.getFullName())
                .age(teacherDto.getAge())
                .tckn(teacherDto.getTckn())
                .lessonId(lesson.getId())
                .build();
    }
}
