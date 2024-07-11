package org.ypecommercesample.schoolhomework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.ypecommercesample.schoolhomework.dto.ClassBranchDto;
import org.ypecommercesample.schoolhomework.entity.ClassBranch;
import org.ypecommercesample.schoolhomework.request.ClassBranchRequest;
import org.ypecommercesample.schoolhomework.response.ClassBranchResponse;
import org.springframework.stereotype.Component;
import org.ypecommercesample.schoolhomework.service.impl.ClassRoomServiceImpl;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class ClassBranchMapper {
    @Autowired
    ClassRoomMapper classRoomMapper;

    @Autowired
    ClassRoomServiceImpl classRoomService;


    @Autowired
    LessonMapper lessonMapper;

    public ClassBranchResponse dtoToResponse(ClassBranchDto dto) {
        return ClassBranchResponse.builder()
                .id(dto.getId())
                .branchName(dto.getBranchName())
                .classRoomId(dto.getClassRoomId())
                .build();
    }

    public ClassBranchDto requestToDto(ClassBranchRequest request) {
        ClassBranchDto classBranchDto = new ClassBranchDto();
        classBranchDto.setClassRoomId(request.getClassRoomId());
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

        // Null kontrolü ekleyin (previously mentioned in your analysis)
        if (classBranchDto.getLessonDtoList() != null) {
            classBranchDto.setLessonDtoList(classBranch.getLessonList().stream().map(lessonMapper::entityToDto).collect(Collectors.toList()));
        } else {
            classBranchDto.setLessonDtoList(new ArrayList<>()); // Boş liste atayın veya ihtiyacınıza göre başka bir işlem yapın
        }

        return classBranchDto;
    }


    public ClassBranch dtoToEntity(ClassBranchDto classBranchDto) {
        ClassBranch classBranch = new ClassBranch();
        classBranch.setId(classBranchDto.getId());
        classBranch.setBranchName(classBranchDto.getBranchName());

        // Null kontrolü ekleyin
        if (classBranchDto.getLessonDtoList() != null) {
            classBranch.setLessonList(classBranchDto.getLessonDtoList().stream()
                    .map(lessonMapper::dtoToEntity)
                    .collect(Collectors.toList()));
        } else {
            classBranch.setLessonList(new ArrayList<>()); // Boş liste atayın veya ihtiyacınıza göre başka bir işlem yapın
        }

        if (classBranchDto.getClassRoomId() != null) {
            classBranch.setClassRoom(classRoomService.findClassRoomById(classBranchDto.getClassRoomId()));
        }

        return classBranch;
    }
}