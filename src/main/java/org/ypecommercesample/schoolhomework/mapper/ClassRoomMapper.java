package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
import org.ypecommercesample.schoolhomework.dto.SchoolDto;
import org.ypecommercesample.schoolhomework.entity.ClassRoom;
import org.ypecommercesample.schoolhomework.request.ClassRoomRequest;
import org.ypecommercesample.schoolhomework.response.ClassRoomResponse;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.service.SchoolService;

@Component
public class ClassRoomMapper {

    @Autowired
    SchoolService schoolService;

    @Autowired
    SchoolService schoolServiceImpl;

    @Autowired
    SchoolMapper schoolMapper;

    public ClassRoomResponse dtoToResponse(ClassRoomDto classRoomDto) {
        return ClassRoomResponse.builder()
                .id(classRoomDto.getId())
                .name(classRoomDto.getName())
                .school(classRoomDto.getSchoolDto())
                .build();
    }

    public ClassRoomDto requestToDto(ClassRoomRequest classRoomRequest) {
        ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setName(classRoomRequest.getName());
        classRoomDto.setSchoolDto(schoolMapper.entityToDto(schoolService.findSchoolById(classRoomRequest.getSchoolId())));
        return classRoomDto;
    }

    public ClassRoomDto entityToDto(ClassRoom classRoom) {
        ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setId(classRoom.getId());
        classRoomDto.setName(classRoom.getName());

        if (classRoom.getSchool() != null && classRoom.getSchool().getId() != null) {
            // Do not call schoolMapper.entityToDto() here directly to avoid recursion
            SchoolDto schoolDto = new SchoolDto();
            schoolDto.setId(classRoom.getSchool().getId());
            schoolDto.setSchoolName(classRoom.getSchool().getSchoolName());
            classRoomDto.setSchoolDto(schoolDto);
        }

        return classRoomDto;
    }

    public ClassRoom dtoToEntity(ClassRoomDto classRoomDto) {
        return ClassRoom.builder()
                .id(classRoomDto.getId())
                .name(classRoomDto.getName())
                .build();
    }
}