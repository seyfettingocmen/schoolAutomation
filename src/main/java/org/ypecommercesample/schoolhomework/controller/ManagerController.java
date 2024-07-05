package org.ypecommercesample.schoolhomework.controller;

import org.springframework.transaction.annotation.Transactional;
import org.ypecommercesample.schoolhomework.dto.ManagerDto;
import org.ypecommercesample.schoolhomework.request.ManagerRequest;
import org.ypecommercesample.schoolhomework.response.ManagerResponse;
import org.ypecommercesample.schoolhomework.service.ManagerService;
import org.ypecommercesample.schoolhomework.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("managers")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @Autowired
    ManagerMapper managerMapper;

    @PostMapping
    public ResponseEntity<ManagerResponse> createManager(@RequestBody ManagerRequest managerRequest) {
        ManagerDto managerDto = managerMapper.requestToDto(managerRequest);
        managerDto = managerService.createManager(managerDto);
        ManagerResponse managerResponse = managerMapper.dtoToResponse(managerDto);
        return ResponseEntity.ok(managerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerResponse> getManagerById(@PathVariable UUID id) {
        ManagerDto managerDto = managerService.getManagerById(id);
        ManagerResponse managerResponse = managerMapper.dtoToResponse(managerDto);
        return ResponseEntity.ok(managerResponse);
    }

    @GetMapping
    public ResponseEntity<List<ManagerResponse>> getAllManagers() {
        List<ManagerDto> managerDtos = managerService.getAllManagers();
        List<ManagerResponse> managerResponses = managerDtos.stream().map(managerMapper::dtoToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(managerResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagerResponse> updateManager(@PathVariable UUID id, @RequestBody ManagerRequest managerRequest) {
        ManagerDto managerDto = managerMapper.requestToDto(managerRequest);
        managerDto = managerService.updateManager(id, managerDto);
        ManagerResponse managerResponse = managerMapper.dtoToResponse(managerDto);
        return ResponseEntity.ok(managerResponse);

    }

    @DeleteMapping("/{id}")
    public void deleteManager(@PathVariable UUID id) {
        managerService.deleteManager(id);
    }
}

