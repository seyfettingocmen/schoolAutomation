package org.ypecommercesample.schoolhomework.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.ypecommercesample.schoolhomework.dto.TeacherDto;
import org.ypecommercesample.schoolhomework.entity.Teacher;
import org.ypecommercesample.schoolhomework.mapper.LessonMapper;
import org.ypecommercesample.schoolhomework.mapper.TeacherMapper;
import org.ypecommercesample.schoolhomework.repository.TeacherRepository;
import org.ypecommercesample.schoolhomework.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private LessonMapper lessonMapper;



    @Override
    @Transactional
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        return teacherMapper.entityToDto(repository.save(teacherMapper.dtoToEntity(teacherDto)));
    }
    @Override
    public TeacherDto getTeacherById(UUID id) {
        Teacher teacher = repository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
        return teacherMapper.entityToDto(teacher);
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        return repository.findAll().stream().map(teacherMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TeacherDto updateTeacher(UUID id, TeacherDto teacherDto) {
        Teacher teacher = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        teacher.setFullName(teacherDto.getFullName());
        teacher.setAge(teacherDto.getAge());
        teacher.setTckn(teacherDto.getTckn());
        teacher.getLesson().setId(teacherDto.getLessonId());
        return teacherMapper.entityToDto(repository.save(teacher));
    }

    @Override
    public void deleteTeacher(UUID id) {
        repository.deleteById(id);
    }

    public Teacher findTeacherById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
    }
}