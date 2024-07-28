package org.ypecommercesample.schoolhomework.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ypecommercesample.schoolhomework.dto.StudentLessonDto;
import org.ypecommercesample.schoolhomework.entity.StudentLesson;
import org.ypecommercesample.schoolhomework.mapper.StudentLessonMapper;
import org.ypecommercesample.schoolhomework.repository.StudentLessonRepository;
import org.ypecommercesample.schoolhomework.service.StudentLessonService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentLessonServiceImpl implements StudentLessonService {

    private final StudentLessonRepository studentLessonRepository;
    private final StudentLessonMapper studentLessonMapper;

    @Autowired
    public StudentLessonServiceImpl(StudentLessonRepository studentLessonRepository, StudentLessonMapper studentLessonMapper) {
        this.studentLessonRepository = studentLessonRepository;
        this.studentLessonMapper = studentLessonMapper;
    }

    @Override
    public StudentLessonDto createStudentLesson(StudentLessonDto studentLessonDto) {
        StudentLesson studentLesson = studentLessonMapper.dtoToEntity(studentLessonDto);
        StudentLesson savedStudentLesson = studentLessonRepository.save(studentLesson);
        return studentLessonMapper.entityToDto(savedStudentLesson);
    }

    @Override
    public StudentLessonDto getStudentLessonById(UUID id) {
        StudentLesson studentLesson = studentLessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StudentLesson not found with id: " + id));
        return studentLessonMapper.entityToDto(studentLesson);
    }

    @Override
    public List<StudentLessonDto> getAllStudentLessons() {
        List<StudentLesson> studentLessons = studentLessonRepository.findAll();
        return studentLessons.stream()
                .map(studentLessonMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentLessonDto updateStudentLesson(UUID id, StudentLessonDto studentLessonDto) {
        StudentLesson existingStudentLesson = studentLessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StudentLesson not found with id: " + id));

        StudentLesson updatedStudentLesson = studentLessonMapper.dtoToEntity(studentLessonDto);
        updatedStudentLesson.setId(existingStudentLesson.getId());

        StudentLesson savedStudentLesson = studentLessonRepository.save(updatedStudentLesson);
        return studentLessonMapper.entityToDto(savedStudentLesson);
    }

    @Override
    public void deleteStudentLesson(UUID id) {
        studentLessonRepository.deleteById(id);
    }

    @Override
    public List<StudentLessonDto> getStudentLessonsByStudentId(UUID studentId) {
        List<StudentLesson> studentLessons = studentLessonRepository.findByStudentId(studentId);
        return studentLessons.stream()
                .map(studentLessonMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentLessonDto> getStudentLessonsByLessonId(UUID lessonId) {
        List<StudentLesson> studentLessons = studentLessonRepository.findByLessonId(lessonId);
        return studentLessons.stream()
                .map(studentLessonMapper::entityToDto)
                .collect(Collectors.toList());
    }

}
