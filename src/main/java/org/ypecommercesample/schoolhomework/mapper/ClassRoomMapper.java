package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.ClassBranchDto;
import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
import org.ypecommercesample.schoolhomework.entity.ClassBranch;
import org.ypecommercesample.schoolhomework.entity.ClassRoom;
import org.ypecommercesample.schoolhomework.entity.School;
import org.ypecommercesample.schoolhomework.request.ClassRoomRequest;
import org.ypecommercesample.schoolhomework.response.ClassRoomResponse;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.service.impl.ClassBranchServiceImpl;
import org.ypecommercesample.schoolhomework.service.impl.SchoolServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassRoomMapper {

    @Autowired
    private SchoolServiceImpl schoolService;

    @Autowired
    private ClassBranchMapper classBranchMapper;

    @Autowired
    private ClassBranchServiceImpl classBranchService;

    // DTO'yu Response'a dönüştüren method
    public ClassRoomResponse dtoToResponse(ClassRoomDto classRoomDto) {
        School school = schoolService.findSchoolById(classRoomDto.getSchoolId());
        List<ClassBranch> classBranches = classBranchService.findAllClassBrances(classRoomDto.getClassBranchDtoList());
        ClassRoomResponse response = new ClassRoomResponse();
        response.setId(classRoomDto.getId());
        response.setName(classRoomDto.getName());
        response.setSchoolId(school.getId());
        response.setClassBranchDtoList(classBranches.stream().map(classBranchMapper::entityToDto).collect(Collectors.toList()));
        return response;
    }

    // Request'i DTO'ya dönüştüren method
    public ClassRoomDto requestToDto(ClassRoomRequest classRoomRequest) {
        School school = schoolService.findSchoolById(classRoomRequest.getSchoolId());
        ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setName(classRoomRequest.getName());
        classRoomDto.setSchoolId(school.getId());
        return classRoomDto;
    }

    // Entity'yi DTO'ya dönüştüren method
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
                    .map(classBranchMapper::entityToDto)
                    .collect(Collectors.toList());
            classRoomDto.setClassBranchDtoList(classBranchDtoList);
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