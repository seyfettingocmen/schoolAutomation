package org.ypecommercesample.schoolhomework.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.ypecommercesample.schoolhomework.dto.SchoolDto;
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
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolMapper schoolMapper;

    @Transactional
    @Override
    public SchoolDto createSchool(SchoolDto schoolDto) {
        School school = schoolMapper.dtoToEntity(schoolDto);
        school = schoolRepository.save(school);
        return schoolMapper.entityToDto(school);
    }

    @Override
    public SchoolDto getSchoolById(UUID id) {
        School school = schoolRepository.findById(id).orElseThrow(() -> new RuntimeException("School not found"));
        return schoolMapper.entityToDto(school);
    }

    @Override
    public List<SchoolDto> getAllSchools() {
        return schoolRepository.findAll().stream().map(schoolMapper::entityToDto).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public SchoolDto updateSchool(UUID id, SchoolDto schoolDto) {
        School school = schoolRepository.findById(id).orElseThrow(() -> new RuntimeException("School not found"));
        school.setSchoolName(schoolDto.getSchoolName());
        school.setId(school.getId());
        school = schoolRepository.save(school);
        return schoolMapper.entityToDto(school);
    }

    @Override
    public void deleteSchool(UUID id) {
        schoolRepository.deleteById(id);
    }

    @Transactional
    public School findSchoolById(UUID id) {
        return schoolRepository.findById(id).orElseThrow(() -> new RuntimeException("School not found"));
    }
}