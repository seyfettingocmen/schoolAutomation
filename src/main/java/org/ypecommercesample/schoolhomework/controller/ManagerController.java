package org.ypecommercesample.schoolhomework.controller;

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

    // HTTP POST isteği ile yeni bir manager oluşturur ve HTTP 200 (OK) durum kodu ile birlikte oluşturulan manager'in bilgilerini döner
    @PostMapping
    public ResponseEntity<ManagerResponse> createManager(@RequestBody ManagerRequest managerRequest) {
        ManagerDto managerDto = managerMapper.requestToDto(managerRequest);
        managerDto = managerService.createManager(managerDto);
        ManagerResponse managerResponse = managerMapper.dtoToResponse(managerDto);
        return ResponseEntity.ok(managerResponse); // Yanıt gövdesi olarak ManagerResponse objesi ve HTTP 200 döner
    }

    // Belirli bir manager'in bilgilerini HTTP 200 (OK) durum kodu ile birlikte döner
    @GetMapping("/{id}")
    public ResponseEntity<ManagerResponse> getManagerById(@PathVariable UUID id) {
        ManagerDto managerDto = managerService.getManagerById(id);
        ManagerResponse managerResponse = managerMapper.dtoToResponse(managerDto);
        return ResponseEntity.ok(managerResponse); // Yanıt gövdesi olarak ManagerResponse objesi ve HTTP 200 döner
    }

    // Tüm manager'ları HTTP 200 (OK) durum kodu ile birlikte döner
    @GetMapping
    public ResponseEntity<List<ManagerResponse>> getAllManagers() {
        List<ManagerDto> managerDtos = managerService.getAllManagers();
        List<ManagerResponse> managerResponses = managerDtos.stream().map(managerMapper::dtoToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(managerResponses); // Yanıt gövdesi olarak ManagerResponse objeleri listesi ve HTTP 200 döner
    }

    // Belirli bir manager'i günceller ve HTTP 200 (OK) durum kodu ile birlikte güncellenmiş manager'in bilgilerini döner
    @PutMapping("/{id}")
    public ResponseEntity<ManagerResponse> updateManager(@PathVariable UUID id, @RequestBody ManagerRequest managerRequest) {
        ManagerDto managerDto = managerMapper.requestToDto(managerRequest);
        managerDto = managerService.updateManager(id, managerDto);
        ManagerResponse managerResponse = managerMapper.dtoToResponse(managerDto);
        return ResponseEntity.ok(managerResponse); // Yanıt gövdesi olarak ManagerResponse objesi ve HTTP 200 döner
    }

    // Belirli bir manager'i siler, HTTP 204 (No Content) durum kodu döner
    @DeleteMapping("/{id}")
    public void deleteManager(@PathVariable UUID id) {
        managerService.deleteManager(id);
        // HTTP 204 (No Content) durum kodu döner, çünkü yanıt gövdesi yoktur
    }
}