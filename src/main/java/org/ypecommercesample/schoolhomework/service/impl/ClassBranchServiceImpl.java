package org.ypecommercesample.schoolhomework.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.ypecommercesample.schoolhomework.dto.ClassBranchDto;
import org.ypecommercesample.schoolhomework.entity.ClassBranch;
import org.ypecommercesample.schoolhomework.entity.ClassRoom;
import org.ypecommercesample.schoolhomework.mapper.ClassBranchMapper;
import org.ypecommercesample.schoolhomework.mapper.LessonMapper;
import org.ypecommercesample.schoolhomework.repository.ClassBranchRepository;
import org.ypecommercesample.schoolhomework.service.ClassBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClassBranchServiceImpl implements ClassBranchService {

    @Autowired
    private ClassBranchRepository repository;

    @Autowired
    private ClassBranchMapper classBranchMapper;

    @Autowired
    private LessonMapper lessonMapper;

    @Transactional
    @Override
    public ClassBranchDto createClassBranch(ClassBranchDto classBranchDto) {
       return classBranchMapper.entityToDto(repository.save(classBranchMapper.dtoToEntity(classBranchDto)));
    }

    @Override
    public ClassBranchDto getClassBranchById(UUID id) {
        ClassBranch classBranch = repository.findById(id).orElseThrow(() -> new RuntimeException("ClassBranch not found"));
        return classBranchMapper.entityToDto(classBranch);
    }

    @Override
    public List<ClassBranchDto> getAllClassBranches() {
        return repository.findAll().stream().map(classBranchMapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ClassBranchDto updateClassBranch(UUID id, ClassBranchDto classBranchDto) {
        ClassBranch classBranch = repository.findById(id).orElseThrow(() -> new RuntimeException("ClassBranch not found"));
        classBranch.setBranchName(classBranchDto.getBranchName());
        classBranch.setId(classBranchDto.getId());
        classBranch.setClassRoom(classBranch.getClassRoom());
        classBranch = repository.save(classBranch);
        return classBranchMapper.entityToDto(classBranch);
    }

    @Override
    public void deleteClassBranch(UUID id) {
        repository.deleteById(id);
    }

    public ClassBranch findClassBranchById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("ClassBranch not found"));
    }

    /*
    public List<ClassBranch> findAllByClassRoomId(UUID classRoomId) {
        return repository.findAllByClassRoom_Id(classRoomId);
    }

     */

    public List<ClassBranchDto> getClassBrachDtoList(ClassRoom classRoom) {
        List<ClassBranchDto> classBranchDtoList = new ArrayList<>();

        // ClassRoom'un ClassBranch koleksiyonunda gezinme
        for (ClassBranch classBranch : classRoom.getClassBranch()) {
            ClassBranchDto classBranchDto = new ClassBranchDto();
            classBranchDto.setId(classBranch.getId());
            classBranchDto.setBranchName(classBranch.getBranchName());
            classBranchDto.setClassRoomId(classRoom.getId()); // classRoomId'yi ClassRoom'dan ayarla

            // Opsiyonel: lessonDtoList'i ClassBranch'te doluysa ekle
            if (classBranch.getLessonList() != null) {
                classBranchDto.setLessonDtoList(classBranch.getLessonList().stream().map(lessonMapper::entityToDto).collect(Collectors.toList())); // lessonDtoList'in bir getter yöntemi olduğunu varsayarak
            }

            classBranchDtoList.add(classBranchDto);
        }

        return classBranchDtoList;
    }

}