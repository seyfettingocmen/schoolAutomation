package org.ypecommercesample.schoolhomework.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.ypecommercesample.schoolhomework.dto.LessonDto;
import org.ypecommercesample.schoolhomework.entity.Lesson;
import org.ypecommercesample.schoolhomework.mapper.ClassBranchMapper;
import org.ypecommercesample.schoolhomework.mapper.LessonMapper;
import org.ypecommercesample.schoolhomework.mapper.StudentMapper;
import org.ypecommercesample.schoolhomework.mapper.TeacherMapper;
import org.ypecommercesample.schoolhomework.repository.LessonRepository;
import org.ypecommercesample.schoolhomework.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository repository;

    @Autowired
    LessonMapper lessonMapper;

    @Autowired
    ClassBranchMapper classBranchMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    StudentMapper studentMapper;

    @Transactional
    @Override
    public LessonDto createLesson(LessonDto lessonDto) {
       return lessonMapper.entityToDto(repository.save(lessonMapper.dtoToEntity(lessonDto)));
    }

    @Override
    public LessonDto getLessonById(UUID id) {
        Lesson lesson = repository.findById(id).orElseThrow(() -> new RuntimeException("Lesson not found"));
        return lessonMapper.entityToDto(lesson);
    }

    @Override
    public List<LessonDto> getAllLessons() {
        return repository.findAll().stream().map(lessonMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public LessonDto updateLesson(UUID id, LessonDto lessonDto) {
        Lesson lesson = repository.findById(id).orElseThrow(() -> new RuntimeException("Lesson not found"));
        lesson.setName(lessonDto.getName());
        lesson.setClassBranch(classBranchMapper.dtoToEntity(lessonDto.getClassBranchDto()));
        lesson.getTeacher().setId(lessonDto.getTeacherId());
        lesson.setStudentList(lessonDto.getStudentDtoList().stream().map(studentMapper::dtoToEntity).collect(Collectors.toList()));
        lesson = repository.save(lesson);
        return lessonMapper.entityToDto(lesson);
    }

    @Override
    public void deleteLesson(UUID id) {
        repository.deleteById(id);
    }

    public Lesson findByLessonId(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Lesson not found"));
    }
}
