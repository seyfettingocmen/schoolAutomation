package org.ypecommercesample.schoolhomework.service.impl;

import org.ypecommercesample.schoolhomework.dto.ClassBranchDto;
import org.ypecommercesample.schoolhomework.entity.ClassBranch;
import org.ypecommercesample.schoolhomework.mapper.ClassBranchMapper;
import org.ypecommercesample.schoolhomework.mapper.ClassRoomMapper;
import org.ypecommercesample.schoolhomework.repository.ClassBranchRepository;
import org.ypecommercesample.schoolhomework.service.ClassBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ClassRoomMapper classRoomMapper;

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

    @Override
    public ClassBranchDto updateClassBranch(UUID id, ClassBranchDto classBranchDto) {
        ClassBranch classBranch = repository.findById(id).orElseThrow(() -> new RuntimeException("ClassBranch not found"));
        classBranch.setBranchName(classBranchDto.getBranchName());
        classBranch.setId(classBranchDto.getId());
        classBranch.setClassRoom(classRoomMapper.dtoToEntity(classBranchDto.getClassRoomDto()));
        classBranch = repository.save(classBranch);
        return classBranchMapper.entityToDto(classBranch);
    }

    @Override
    public void deleteClassBranch(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public ClassBranch findClassBranchById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("ClassBranch not found"));
    }
}