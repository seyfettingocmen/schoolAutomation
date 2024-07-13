package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.ClassBranchDto;
import org.ypecommercesample.schoolhomework.entity.ClassBranch;
import org.ypecommercesample.schoolhomework.entity.ClassRoom;
import org.ypecommercesample.schoolhomework.entity.Lesson;
import org.ypecommercesample.schoolhomework.request.ClassBranchRequest;
import org.ypecommercesample.schoolhomework.response.ClassBranchResponse;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.service.impl.ClassRoomServiceImpl;
import org.ypecommercesample.schoolhomework.service.impl.LessonServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassBranchMapper {

    @Autowired
    private ClassRoomServiceImpl classRoomService;
    @Autowired
    private LessonMapper lessonMapper;
    @Autowired
    private LessonServiceImpl lessonService;


    public ClassBranchResponse dtoToResponse(ClassBranchDto dto) {
        ClassRoom classRoom = classRoomService.findClassRoomById(dto.getClassRoomId());

        return ClassBranchResponse.builder()
                .id(dto.getId())
                .branchName(dto.getBranchName())
                .classRoomId(classRoom.getId())
                .lessonDtoList(dto.getLessonDtoList())
                .build();
    }


    public ClassBranchDto requestToDto(ClassBranchRequest request) {
        ClassRoom classRoom = classRoomService.findClassRoomById(request.getClassRoomId());
        ClassBranchDto classBranchDto = new ClassBranchDto();
        classBranchDto.setClassRoomId(classRoom.getId());
        classBranchDto.setBranchName(request.getBranchName());
        return classBranchDto;
    }

    public ClassBranchDto entityToDto(ClassBranch classBranch) {
        ClassBranchDto classBranchDto = new ClassBranchDto();
        classBranchDto.setId(classBranch.getId());
        classBranchDto.setBranchName(classBranch.getBranchName());

        // Set ClassRoomDto only if not already set during a recursive call
        if (classBranch.getClassRoom() != null && classBranchDto.getClassRoomId() == null) {
            classBranchDto.setClassRoomId(classBranch.getClassRoom().getId());
        }

        // Null checks for lesson list
        if (classBranch.getLessonList() != null) {
            classBranchDto.setLessonDtoList(classBranch.getLessonList().stream()
                    .map(lessonMapper::entityToDto)
                    .collect(Collectors.toList()));
        }

        return classBranchDto;
    }


    public ClassBranch dtoToEntity(ClassBranchDto classBranchDto) {
        ClassBranch classBranch = new ClassBranch();
        classBranch.setId(classBranchDto.getId());
        classBranch.setBranchName(classBranchDto.getBranchName());

        // Null checks for lesson list
        if (classBranchDto.getLessonDtoList() != null) {
            classBranch.setLessonList(classBranchDto.getLessonDtoList().stream().map(lessonMapper::dtoToEntity).collect(Collectors.toList()));
        }
        classBranch.setClassRoom(classRoomService.findClassRoomById(classBranchDto.getClassRoomId()));
        return classBranch;
    }
}
