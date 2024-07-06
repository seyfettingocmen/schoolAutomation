package org.ypecommercesample.schoolhomework.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
import org.ypecommercesample.schoolhomework.dto.SchoolDto;
import org.ypecommercesample.schoolhomework.entity.ClassRoom;
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

    public SchoolResponse dtoToResponse(SchoolDto schoolDto) {
        return SchoolResponse.builder()
                .id(schoolDto.getId())
                .schoolName(schoolDto.getSchoolName())
                .classRoomDtoList(schoolDto.getClassRoomDtoList())
                .build();
    }

    public SchoolDto requestToDto(SchoolRequest schoolRequest) {
        return SchoolDto.builder()
                .schoolName(schoolRequest.getSchoolName())
                .build();
    }

    public SchoolDto entityToDto(School school) {
        SchoolDto schoolDto = SchoolDto.builder()
                .id(school.getId())
                .schoolName(school.getSchoolName())
                .build();

        if (school.getClassRoomList() != null) {
            List<ClassRoomDto> classRoomDtoList = new ArrayList<>();
            for (ClassRoom classRoom : school.getClassRoomList()) {
                ClassRoomDto classRoomDto = new ClassRoomDto();
                classRoomDto.setId(classRoom.getId());
                classRoomDto.setName(classRoom.getName());
                if (classRoom.getSchool() != null && classRoom.getSchool().getId() != null) {
                    classRoomDto.setSchoolDto(entityToDto(classRoom.getSchool())); // Recursive call corrected
                }
                classRoomDtoList.add(classRoomDto);
            }
            schoolDto.setClassRoomDtoList(classRoomDtoList);
        } else {
            schoolDto.setClassRoomDtoList(Collections.emptyList());
        }

        return schoolDto;
    }


    public School dtoToEntity(SchoolDto schoolDto) {
        return School.builder()
                .id(schoolDto.getId())
                .schoolName(schoolDto.getSchoolName())

                .classRoomList(schoolDto.getClassRoomDtoList() != null ?
                        schoolDto.getClassRoomDtoList().stream().map(classRoomMapper::dtoToEntity).collect(Collectors.toList()) :
                        Collections.emptyList())
                .build();
    }
}