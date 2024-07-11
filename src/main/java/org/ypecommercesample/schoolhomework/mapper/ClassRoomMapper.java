package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.ClassBranchDto;
import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
import org.ypecommercesample.schoolhomework.dto.SchoolDto;
import org.ypecommercesample.schoolhomework.entity.ClassRoom;
import org.ypecommercesample.schoolhomework.entity.School;
import org.ypecommercesample.schoolhomework.request.ClassRoomRequest;
import org.ypecommercesample.schoolhomework.response.ClassRoomResponse;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.service.impl.SchoolServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ClassRoomMapper {

    @Autowired
    private SchoolServiceImpl schoolService;

    @Autowired
    private ClassBranchMapper classBranchMapper;


    // DTO'yu Response'a dönüştüren method
    public ClassRoomResponse dtoToResponse(ClassRoomDto dto) {
        School school = schoolService.findSchoolById(dto.getSchoolId());
        ClassRoomResponse response = new ClassRoomResponse();
        response.setId(dto.getId());
        response.setName(dto.getName());
        response.setSchoolId(school.getId());
        response.setClassBranchDtoList(dto.getClassBranchDtoList());
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
            SchoolDto schoolDto = new SchoolDto();
            schoolDto.setId(classRoom.getSchool().getId());
            schoolDto.setSchoolName(classRoom.getSchool().getSchoolName());
            classRoomDto.setSchoolId(schoolDto.getId());  // SchoolDto doğru şekilde set ediliyor
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
        classRoom.setSchool(schoolService.findSchoolById(classRoomDto.getSchoolId()));

        // Null kontrolü ekleyelim
        if (classRoomDto.getClassBranchDtoList() != null) {
            classRoom.setClassBranch(classRoomDto.getClassBranchDtoList().stream()
                    .map(classBranchMapper::dtoToEntity)
                    .collect(Collectors.toList()));
        } else {
            classRoom.setClassBranch(new ArrayList<>()); // Eğer null ise boş bir liste set ediyoruz
        }

        return classRoom;
    }


}