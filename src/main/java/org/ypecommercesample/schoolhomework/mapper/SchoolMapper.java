package org.ypecommercesample.schoolhomework.mapper;

import org.ypecommercesample.schoolhomework.dto.SchoolDto;
import org.ypecommercesample.schoolhomework.entity.School;
import org.ypecommercesample.schoolhomework.request.SchoolRequest;
import org.ypecommercesample.schoolhomework.response.SchoolResponse;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

    public SchoolResponse dtoToResponse(SchoolDto dto) {
        return SchoolResponse.builder()
                .id(dto.getId())
                .schoolName(dto.getSchoolName())
                .build();
    }

    public SchoolDto requestToDto(SchoolRequest request) {
        return SchoolDto.builder()
                .schoolName(request.getSchoolName())
                .build();
    }

    public SchoolDto entityToDto(School entity) {
        return SchoolDto.builder()
                .id(entity.getId())
                .schoolName(entity.getSchoolName())
                .build();
    }

    public School dtoToEntity(SchoolDto dto) {
        return School.builder()
                .id(dto.getId())
                .schoolName(dto.getSchoolName())
                .build();
    }
}