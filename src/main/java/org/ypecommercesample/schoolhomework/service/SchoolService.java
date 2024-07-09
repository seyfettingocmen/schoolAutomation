package org.ypecommercesample.schoolhomework.service;

import org.ypecommercesample.schoolhomework.dto.SchoolDto;

import java.util.List;
import java.util.UUID;

public interface SchoolService {
    SchoolDto createSchool(SchoolDto schoolDto);
    SchoolDto getSchoolById(UUID id);
    List<SchoolDto> getAllSchools();
    SchoolDto updateSchool(UUID id, SchoolDto schoolDto);
    void deleteSchool(UUID id);
}