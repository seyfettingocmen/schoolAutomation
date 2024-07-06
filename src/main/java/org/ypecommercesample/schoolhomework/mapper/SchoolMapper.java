package org.ypecommercesample.schoolhomework.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
import org.ypecommercesample.schoolhomework.dto.ManagerDto;
import org.ypecommercesample.schoolhomework.dto.SchoolDto;
import org.ypecommercesample.schoolhomework.entity.ClassRoom;
import org.ypecommercesample.schoolhomework.entity.Manager;
import org.ypecommercesample.schoolhomework.entity.School;
import org.ypecommercesample.schoolhomework.request.SchoolRequest;
import org.ypecommercesample.schoolhomework.response.SchoolResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

@Component
public class SchoolMapper {

    @Autowired
    ClassRoomMapper classRoomMapper;

    @Autowired
    ManagerMapper managerMapper;

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
            List<ClassRoomDto> classRoomDtoList = new ArrayList<>();
            for (ClassRoom classRoom : school.getClassRoomList()) {
                ClassRoomDto classRoomDto = new ClassRoomDto();
                classRoomDto.setId(classRoom.getId());
                classRoomDto.setName(classRoom.getName());
                // Recursive call corrected: Avoid infinite recursion
                if (classRoom.getSchool() != null && classRoom.getSchool().getId() != null && !classRoom.getSchool().getId().equals(school.getId())) {
                    classRoomDto.setSchoolDto(entityToDto(classRoom.getSchool()));
                }
                classRoomDtoList.add(classRoomDto);
            }
            schoolDto.setClassRoomDtoList(classRoomDtoList);
        } else {
            schoolDto.setClassRoomDtoList(Collections.emptyList());
        }

        // managerList null değilse, her Manager entity'sini ManagerDto'ya dönüştürür ve schoolDto'ya ekler
        if (school.getManagerList() != null) {
            List<ManagerDto> managerDtoList = new ArrayList<>();
            for (Manager manager : school.getManagerList()) {
                ManagerDto managerDto = new ManagerDto();
                managerDto.setId(manager.getId());
                managerDto.setFullName(manager.getFullName());
                managerDto.setAge(manager.getAge());
                managerDto.setAuthority(manager.getAuthority());
                managerDto.setTckn(manager.getTckn());
                // Recursive call corrected: Avoid infinite recursion
                if (manager.getSchool() != null && manager.getSchool().getId() != null && !manager.getSchool().getId().equals(school.getId())) {
                    managerDto.setSchoolId(school.getId());
                }
                managerDtoList.add(managerDto);
            }
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