package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.ManagerDto;
import org.ypecommercesample.schoolhomework.entity.Manager;
import org.ypecommercesample.schoolhomework.entity.School;
import org.ypecommercesample.schoolhomework.request.ManagerRequest;
import org.ypecommercesample.schoolhomework.response.ManagerResponse;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.service.ManagerService;
import org.ypecommercesample.schoolhomework.service.SchoolService;



@Component
public class ManagerMapper {

    @Autowired
    SchoolService schoolService;

    @Autowired
    ManagerService managerService;

    @Autowired
    SchoolMapper schoolMapper;

    public ManagerResponse dtoToResponse(ManagerDto managerDto) {
        School school = schoolService.findSchoolById(managerDto.getSchoolId());
        return ManagerResponse.builder()
                .id(managerDto.getId())
                .fullName(managerDto.getFullName())
                .tckn(managerDto.getTckn())
                .age(managerDto.getAge())
                .authority(managerDto.getAuthority())
                .schoolId(school.getId())
                .build();
    }

    public ManagerDto requestToDto(ManagerRequest managerRequest) {
        School school = schoolService.findSchoolById(managerRequest.getSchoolId());
        return ManagerDto.builder()
                .fullName(managerRequest.getFullName())
                .age(managerRequest.getAge())
                .tckn(managerRequest.getTckn())
                .authority(managerRequest.getAuthority())
                .schoolId(school.getId())
                .build();
    }


    public Manager dtoToEntity(ManagerDto managerDto) {
        Manager entity = new Manager();
        entity.setFullName(managerDto.getFullName());
        entity.setAge(managerDto.getAge());
        entity.setTckn(managerDto.getTckn());
        entity.setAuthority(managerDto.getAuthority());
        if (managerDto.getSchoolId() != null) {
            entity.setSchool(schoolService.findSchoolById(managerDto.getSchoolId()));
        }
        return entity;
    }

    public ManagerDto entityToDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setFullName(manager.getFullName());
        managerDto.setAge(manager.getAge());
        managerDto.setTckn(manager.getTckn());
        managerDto.setAuthority(manager.getAuthority());
        if (manager.getSchool() != null) {
            managerDto.setSchoolId(manager.getSchool().getId());
        }
        return managerDto;
    }
}