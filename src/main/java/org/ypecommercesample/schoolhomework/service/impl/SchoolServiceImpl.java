package org.ypecommercesample.schoolhomework.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.ypecommercesample.schoolhomework.dto.SchoolDto;
import org.ypecommercesample.schoolhomework.entity.ClassRoom;
import org.ypecommercesample.schoolhomework.entity.Manager;
import org.ypecommercesample.schoolhomework.entity.School;
import org.ypecommercesample.schoolhomework.mapper.SchoolMapper;
import org.ypecommercesample.schoolhomework.repository.SchoolRepository;
import org.ypecommercesample.schoolhomework.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository repository;

    @Autowired
    private SchoolMapper schoolMapper;


    @Transactional
    @Override
    public SchoolDto createSchool(SchoolDto schoolDto) {
        School school = schoolMapper.dtoToEntity(schoolDto);
        school = repository.save(school);
        return schoolMapper.entityToDto(school);
    }

    @Override
    public SchoolDto getSchoolById(UUID id) {
        School school = repository.findById(id).orElseThrow(() -> new RuntimeException("School not found"));
        return schoolMapper.entityToDto(school);
    }

    @Override
    public List<SchoolDto> getAllSchools() {
        return repository.findAll().stream().map(schoolMapper::entityToDto).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public SchoolDto updateSchool(UUID id, SchoolDto schoolDto) {
        School existingSchool = repository.findById(id).orElseThrow(() -> new RuntimeException("School not found"));
        existingSchool.setSchoolName(schoolDto.getSchoolName());
       return schoolMapper.entityToDto(repository.save(existingSchool));
    }
    @Transactional
    @Override
    public void deleteSchool(UUID id) {
        School school = repository.findById(id).orElseThrow();

        // 1. School ve ClassRoom İlişkisini Kırma
        for (ClassRoom classroom : school.getClassRoomList()) {
            classroom.setSchool(null); // İlişkiyi kes
        }

        for (Manager manager : school.getManagerList()) {
            manager.setSchool(null);
        }

        // 3. School'u Silme
        repository.deleteById(id);
    }




    @Transactional
    public School findSchoolById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("School not found"));
    }
}