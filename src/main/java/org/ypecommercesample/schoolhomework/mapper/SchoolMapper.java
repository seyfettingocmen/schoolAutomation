package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
import org.ypecommercesample.schoolhomework.dto.ManagerDto;
import org.ypecommercesample.schoolhomework.dto.SchoolDto;
import org.ypecommercesample.schoolhomework.entity.School;
import org.ypecommercesample.schoolhomework.request.SchoolRequest;
import org.ypecommercesample.schoolhomework.response.SchoolResponse;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.service.impl.ClassRoomServiceImpl;
import org.ypecommercesample.schoolhomework.service.impl.ManagerServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

@Component
public class SchoolMapper {

    @Autowired
    private ClassRoomMapper classRoomMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private ClassRoomServiceImpl classRoomService;

    @Autowired
    private ManagerServiceImpl managerService;

    public SchoolResponse dtoToResponse(SchoolDto schoolDto) {
        return SchoolResponse.builder()
                .id(schoolDto.getId())
                .schoolName(schoolDto.getSchoolName())
                .classRoomDtoList(schoolDto.getClassRoomDtoList())
                .managerDtoList(schoolDto.getManagerDtoList())
                .build();
    }

    public SchoolDto requestToDto(SchoolRequest schoolRequest) {
        return SchoolDto.builder()
                .schoolName(schoolRequest.getSchoolName())
                .build();
    }

    @Transactional // Bu anotasyon metot çağrısı sırasında bir Hibernate oturumunun açık kalmasını sağlar
    public SchoolDto entityToDto(School school) {
        // School entity'sini SchoolDto'ya dönüştürür
        SchoolDto schoolDto = SchoolDto.builder()
                .id(school.getId())
                .schoolName(school.getSchoolName())
                .build();

        // classRoomList null değilse, her ClassRoom entity'sini ClassRoomDto'ya dönüştürür ve schoolDto'ya ekler
        if (school.getClassRoomList() != null) {
            List<ClassRoomDto> classRoomDtoList = classRoomService.getClassRoomDtoList(school);
            schoolDto.setClassRoomDtoList(classRoomDtoList);
        } else {
            schoolDto.setClassRoomDtoList(Collections.emptyList());
        }

        // managerList null değilse, her Manager entity'sini ManagerDto'ya dönüştürür ve schoolDto'ya ekler
        if (school.getManagerList() != null) {
            List<ManagerDto> managerDtoList = managerService.getManagerDtoList(school);
            schoolDto.setManagerDtoList(managerDtoList); // managerDtoList'in schoolDto'ya eklenmesi unutulmuştu, düzeltildi
        } else {
            schoolDto.setManagerDtoList(Collections.emptyList());
        }

        return schoolDto;
    }

    public School dtoToEntity(SchoolDto schoolDto) {
        return School.builder()
                .id(schoolDto.getId())
                .schoolName(schoolDto.getSchoolName())
                .managerList(schoolDto.getManagerDtoList() != null ?
                        schoolDto.getManagerDtoList().stream().map(managerMapper::dtoToEntity).collect(Collectors.toList()) :
                        Collections.emptyList())
                .classRoomList(schoolDto.getClassRoomDtoList() != null ?
                        schoolDto.getClassRoomDtoList().stream().map(classRoomMapper::dtoToEntity).collect(Collectors.toList()) :
                        Collections.emptyList())
                .build();
    }
}