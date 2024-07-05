package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
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
    SchoolMapper schoolMapper;
    public ClassRoomResponse dtoToResponse(ClassRoomDto dto) {
        return ClassRoomResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .school(dto.getSchoolId())
                .build();
    }

    public ClassRoomDto requestToDto(ClassRoomRequest request) {
        ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setName(request.getName());
        classRoomDto.setSchoolId(schoolMapper.entityToDto(schoolService.findSchoolById(request.getSchoolId())));
        return classRoomDto;
    }

    public ClassRoomDto entityToDto(ClassRoom entity) {
        ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setId(entity.getId());
        classRoomDto.setName(entity.getName());
        classRoomDto.setSchoolId(schoolMapper.entityToDto(entity.getSchool()));
        return classRoomDto;

    }

    public ClassRoom dtoToEntity(ClassRoomDto dto) {
        return ClassRoom.builder()
                .id(dto.getId())
                .name(dto.getName())
                .school(schoolMapper.dtoToEntity(dto.getSchoolId()))
                .build();
    }
}