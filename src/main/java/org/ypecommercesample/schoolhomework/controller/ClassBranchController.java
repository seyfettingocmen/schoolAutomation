package org.ypecommercesample.schoolhomework.controller;

import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("classbranches")
public class ClassBranchController {
    @Autowired
    private ClassBranchService classBranchService;

    @Autowired
    private ClassBranchMapper classBranchMapper;

    // HTTP POST isteği ile yeni bir class branch oluşturur ve doğrudan ClassBranchResponse döner
    @Transactional
    @PostMapping
    public ClassBranchResponse createClassBranch(@RequestBody ClassBranchRequest classBranchRequest) {
        return classBranchMapper.dtoToResponse(classBranchService.createClassBranch(classBranchMapper.requestToDto(classBranchRequest)));
    }

    // Belirli bir class branch'in bilgilerini HTTP 200 (OK) durum kodu ile birlikte döner
    @GetMapping("/{id}")
    public ResponseEntity<ClassBranchResponse> getClassBranchById(@PathVariable UUID id) {
        ClassBranchDto classBranchDto = classBranchService.getClassBranchById(id);
        ClassBranchResponse classBranchResponse = classBranchMapper.dtoToResponse(classBranchDto);
        return ResponseEntity.ok(classBranchResponse); // Yanıt gövdesi olarak ClassBranchResponse objesi ve HTTP 200 döner
    }

    // Tüm class branch'leri HTTP 200 (OK) durum kodu ile birlikte döner
    @GetMapping
    public ResponseEntity<List<ClassBranchResponse>> getAllClassBranches() {
        List<ClassBranchDto> classBranchDtos = classBranchService.getAllClassBranches();
        List<ClassBranchResponse> classBranchResponses = classBranchDtos.stream().map(classBranchMapper::dtoToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(classBranchResponses); // Yanıt gövdesi olarak ClassBranchResponse objeleri listesi ve HTTP 200 döner
    }

    // Belirli bir class branch'i günceller ve HTTP 200 (OK) durum kodu ile birlikte güncellenmiş class branch'in bilgilerini döner
    @PutMapping("/{id}")
    public ResponseEntity<ClassBranchResponse> updateClassBranch(@PathVariable UUID id, @RequestBody ClassBranchRequest classBranchRequest) {
        ClassBranchDto classBranchDto = classBranchMapper.requestToDto(classBranchRequest);
        classBranchDto = classBranchService.updateClassBranch(id, classBranchDto);
        ClassBranchResponse classBranchResponse = classBranchMapper.dtoToResponse(classBranchDto);
        return ResponseEntity.ok(classBranchResponse); // Yanıt gövdesi olarak ClassBranchResponse objesi ve HTTP 200 döner
    }

    // Belirli bir class branch'i siler ve HTTP 204 (No Content) durum kodu döner
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassBranch(@PathVariable UUID id) {
        classBranchService.deleteClassBranch(id);
        return ResponseEntity.noContent().build(); // Yanıt gövdesi olmadan HTTP 204 döner
    }
}