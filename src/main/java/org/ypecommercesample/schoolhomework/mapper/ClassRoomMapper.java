package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.ypecommercesample.schoolhomework.dto.ClassBranchDto;
import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
import org.ypecommercesample.schoolhomework.entity.ClassRoom;
import org.ypecommercesample.schoolhomework.entity.School;
import org.ypecommercesample.schoolhomework.request.ClassRoomRequest;
import org.ypecommercesample.schoolhomework.response.ClassRoomResponse;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.service.impl.SchoolServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassRoomMapper {

    @Autowired
    private SchoolServiceImpl schoolService;

    @Autowired
    private ClassBranchMapper classBranchMapper;

    // DTO'yu Response'a dönüştüren method
    public ClassRoomResponse dtoToResponse(ClassRoomDto classRoomDto) {
        School school = schoolService.findSchoolById(classRoomDto.getSchoolId());
        ClassRoomResponse classRoomResponse = new ClassRoomResponse();
        classRoomResponse.setId(classRoomDto.getId());
        classRoomResponse.setName(classRoomDto.getName());
        classRoomResponse.setSchoolId(school.getId());

        // Sadece çekilen ClassRoom'a ait ClassBranch'leri listeye ekle
        if (classRoomDto.getClassBranchDtoList() != null) {
            List<ClassBranchDto> classBranchDtoList = classRoomDto.getClassBranchDtoList().stream()
                    .filter(classBranchDto -> classBranchDto.getClassRoomId().equals(classRoomDto.getId()))
                    .collect(Collectors.toList());
            classRoomResponse.setClassBranchDtoList(classBranchDtoList);
        } else {
            classRoomResponse.setClassBranchDtoList(Collections.emptyList());
        }

        return classRoomResponse;
    }


    // Request'i DTO'ya dönüştüren method
    public ClassRoomDto requestToDto(ClassRoomRequest classRoomRequest) {
        School school = schoolService.findSchoolById(classRoomRequest.getSchoolId());
        ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setName(classRoomRequest.getName());
        classRoomDto.setSchoolId(school.getId());
        return classRoomDto;
    }

    @Transactional
    // Entity'yi DTO'ya dönüştüren method
    public ClassRoomDto entityToDto(ClassRoom classRoom) {
        ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setId(classRoom.getId());
        classRoomDto.setName(classRoom.getName());

        // School nesnesi ve SchoolDto
        if (classRoom.getSchool() != null && classRoom.getSchool().getId() != null) {
            School school = schoolService.findSchoolById(classRoom.getSchool().getId());
            classRoomDto.setSchoolId(school.getId());
        }

        // ClassBranch listesi dönüştürülüyor
        if (classRoom.getClassBranch() != null) {
            List<ClassBranchDto> classBranchDtoList = classRoom.getClassBranch().stream()
                    .filter(classBranch -> classBranch.getClassRoom().getId().equals(classRoom.getId()))
                    .map(classBranchMapper::entityToDto)
                    .collect(Collectors.toList());
            classRoomDto.setClassBranchDtoList(classBranchDtoList);
        } else {
            classRoomDto.setClassBranchDtoList(Collections.emptyList());
        }

        return classRoomDto;
    }

    // DTO'yu Entity'ye dönüştüren method
    public ClassRoom dtoToEntity(ClassRoomDto classRoomDto) {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setId(classRoomDto.getId());
        classRoom.setName(classRoomDto.getName());

        if(classRoomDto.getSchoolId() != null) {
            classRoom.setSchool(schoolService.findSchoolById(classRoomDto.getSchoolId()));
        }
        // Null kontrolü ekleyelim
        if (classRoomDto.getClassBranchDtoList() != null) {
            classRoom.setClassBranch(classRoomDto.getClassBranchDtoList().stream()
                    .map(classBranchMapper::dtoToEntity)
                    .collect(Collectors.toList()));
        }
        return classRoom;
    }


}