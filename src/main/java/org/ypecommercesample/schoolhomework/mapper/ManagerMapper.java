package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.ManagerDto;
import org.ypecommercesample.schoolhomework.entity.Manager;
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
        return ManagerResponse.builder()
                .id(managerDto.getId())
                .fullName(managerDto.getFullName())
                .tckn(managerDto.getTckn())
                .age(managerDto.getAge())
                .authority(managerDto.getAuthority())
                .schoolResponse(schoolMapper.dtoToResponse(managerDto.getSchoolDto()))
                .build();
    }

    public ManagerDto requestToDto(ManagerRequest managerRequest) {
        return ManagerDto.builder()
                .fullName(managerRequest.getFullName())
                .age(managerRequest.getAge())
                .tckn(managerRequest.getTckn())
                .authority(managerRequest.getAuthority())
                .schoolDto(schoolMapper.entityToDto(schoolService.findSchoolById(managerRequest.getSchoolId())))
                .build();
    }


    public Manager dtoToEntity(ManagerDto managerDto) {
        Manager entity = new Manager();
        entity.setFullName(managerDto.getFullName());
        entity.setAge(managerDto.getAge());
        entity.setTckn(managerDto.getTckn());
        entity.setAuthority(managerDto.getAuthority());
        if (managerDto.getSchoolDto() != null) {
            entity.setSchool(schoolService.findSchoolById(managerDto.getSchoolDto().getId()));
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
            managerDto.setSchoolDto(schoolMapper.entityToDto(schoolService.findSchoolById(manager.getSchool().getId())));
        }
        return managerDto;
    }
}