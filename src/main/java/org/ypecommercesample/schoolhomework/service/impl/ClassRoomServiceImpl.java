package org.ypecommercesample.schoolhomework.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
import org.ypecommercesample.schoolhomework.entity.ClassRoom;
import org.ypecommercesample.schoolhomework.mapper.ClassRoomMapper;
import org.ypecommercesample.schoolhomework.mapper.SchoolMapper;
import org.ypecommercesample.schoolhomework.repository.ClassRoomRepository;
import org.ypecommercesample.schoolhomework.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassRoomRepository repository;

    @Autowired
    private ClassRoomMapper classRoomMapper;


    @Autowired
    private SchoolServiceImpl schoolServiceImpl;
    @Transactional
    @Override
    public ClassRoomDto createClassRoom(ClassRoomDto classRoomDto) {
        return classRoomMapper.entityToDto(repository.save(classRoomMapper.dtoToEntity(classRoomDto)));
    }

    @Override
    public ClassRoomDto getClassRoomById(UUID id) {
        ClassRoom classRoom = repository.findById(id).orElseThrow(() -> new RuntimeException("ClassRoom not found"));
        return classRoomMapper.entityToDto(classRoom);
    }

    @Override
    public List<ClassRoomDto> getAllClassRooms() {
        return repository.findAll().stream().map(classRoomMapper::entityToDto).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public ClassRoomDto updateClassRoom(UUID id, ClassRoomDto classRoomDto) {
        ClassRoom classRoom = repository.findById(id).orElseThrow(() -> new RuntimeException("ClassRoom not found"));
        classRoom.setName(classRoomDto.getName());
        classRoom.setSchool(schoolServiceImpl.findSchoolById(classRoomDto.getSchoolId()));
        classRoom = repository.save(classRoom);
        return classRoomMapper.entityToDto(classRoom);
    }

    @Override
    public void deleteClassRoom(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public ClassRoom findClassRoomById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("ClassRoom not found"));
    }
}