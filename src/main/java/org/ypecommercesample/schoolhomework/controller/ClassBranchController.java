package org.ypecommercesample.schoolhomework.controller;

import org.ypecommercesample.schoolhomework.dto.ClassBranchDto;
import org.ypecommercesample.schoolhomework.request.ClassBranchRequest;
import org.ypecommercesample.schoolhomework.response.ClassBranchResponse;
import org.ypecommercesample.schoolhomework.service.ClassBranchService;
import org.ypecommercesample.schoolhomework.mapper.ClassBranchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("classbraches")
public class ClassBranchController {
    @Autowired
    private ClassBranchService classBranchService;

    @Autowired
    private ClassBranchMapper classBranchMapper;

    @PostMapping
    public ClassBranchResponse createClassBranch(@RequestBody ClassBranchRequest classBranchRequest) {
        return classBranchMapper.dtoToResponse(classBranchService.createClassBranch(classBranchMapper.requestToDto(classBranchRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassBranchResponse> getClassBranchById(@PathVariable UUID id) {
        ClassBranchDto classBranchDto = classBranchService.getClassBranchById(id);
        ClassBranchResponse classBranchResponse = classBranchMapper.dtoToResponse(classBranchDto);
        return ResponseEntity.ok(classBranchResponse);
    }

    @GetMapping
    public ResponseEntity<List<ClassBranchResponse>> getAllClassBranches() {
        List<ClassBranchDto> classBranchDtos = classBranchService.getAllClassBranches();
        List<ClassBranchResponse> classBranchResponses = classBranchDtos.stream().map(classBranchMapper::dtoToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(classBranchResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassBranchResponse> updateClassBranch(@PathVariable UUID id, @RequestBody ClassBranchRequest classBranchRequest) {
        ClassBranchDto classBranchDto = classBranchMapper.requestToDto(classBranchRequest);
        classBranchDto = classBranchService.updateClassBranch(id, classBranchDto);
        ClassBranchResponse classBranchResponse = classBranchMapper.dtoToResponse(classBranchDto);
        return ResponseEntity.ok(classBranchResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassBranch(@PathVariable UUID id) {
        classBranchService.deleteClassBranch(id);
        return ResponseEntity.noContent().build();
    }
}