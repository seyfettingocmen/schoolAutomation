package org.ypecommercesample.schoolhomework.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.ypecommercesample.schoolhomework.dto.StudentDto;
import org.ypecommercesample.schoolhomework.entity.Student;
import org.ypecommercesample.schoolhomework.mapper.StudentLessonMapper;
import org.ypecommercesample.schoolhomework.mapper.StudentMapper;
import org.ypecommercesample.schoolhomework.repository.StudentRepository;
import org.ypecommercesample.schoolhomework.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentLessonMapper studentLessonMapper;

    @Override
    @Transactional
    public StudentDto createStudent(StudentDto studentDto) {
        return studentMapper.entityToDto(repository.save(studentMapper.dtoToEntity(studentDto)));
    }

    @Override
    public StudentDto getStudentById(UUID id) {
        Student student = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.entityToDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return repository.findAll().stream().map(studentMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentDto updateStudent(UUID id, StudentDto studentDto) {
        Student student = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setFullName(studentDto.getFullName());
        student.setAge(studentDto.getAge());
        student.setTckn(studentDto.getTckn());
        student.setStudentLessons(studentDto.getStudentLessons().stream().map(studentLessonMapper::dtoToEntity).collect(Collectors.toList()));
        return studentMapper.entityToDto(repository.save(student));
    }
    @Transactional
    @Override
    public void deleteStudent(UUID id) {
        repository.deleteById(id);
    }
    /*
    public Student findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }
     */
}