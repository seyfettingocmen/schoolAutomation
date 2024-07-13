package org.ypecommercesample.schoolhomework.controller;

import org.ypecommercesample.schoolhomework.dto.SchoolDto;
import org.ypecommercesample.schoolhomework.request.SchoolRequest;
import org.ypecommercesample.schoolhomework.response.SchoolResponse;
import org.ypecommercesample.schoolhomework.service.SchoolService;
import org.ypecommercesample.schoolhomework.mapper.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private SchoolMapper schoolMapper;

    // HTTP POST isteği ile yeni bir okul yaratır ve HTTP 200 (OK) durum kodu ile birlikte yaratılan okulun bilgilerini döner
    @PostMapping
    public ResponseEntity<SchoolResponse> createSchool(@RequestBody SchoolRequest schoolRequest) {
        SchoolDto schoolDto = schoolMapper.requestToDto(schoolRequest);
        schoolDto = schoolService.createSchool(schoolDto);
        SchoolResponse schoolResponse = schoolMapper.dtoToResponse(schoolDto);
        return ResponseEntity.ok(schoolResponse); // Yanıt gövdesi olarak SchoolResponse objesi ve HTTP 200 döner
    }

    // Belirli bir okulun bilgilerini HTTP 200 (OK) durum kodu ile birlikte döner
    @GetMapping("/{id}")
    public ResponseEntity<SchoolResponse> getSchoolById(@PathVariable UUID id) {
        SchoolDto schoolDto = schoolService.getSchoolById(id);
        SchoolResponse schoolResponse = schoolMapper.dtoToResponse(schoolDto);
        return ResponseEntity.ok(schoolResponse); // Yanıt gövdesi olarak SchoolResponse objesi ve HTTP 200 döner
    }

    // Tüm okulları HTTP 200 (OK) durum kodu ile birlikte döner
    @GetMapping
    public ResponseEntity<List<SchoolResponse>> getAllSchools() {
        List<SchoolDto> schoolDtos = schoolService.getAllSchools();
        List<SchoolResponse> schoolResponses = schoolDtos.stream().map(schoolMapper::dtoToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(schoolResponses); // Yanıt gövdesi olarak SchoolResponse objeleri listesi ve HTTP 200 döner
    }

    // Belirli bir okulu günceller ve HTTP 200 (OK) durum kodu ile birlikte güncellenmiş okulun bilgilerini döner
    @PutMapping("/{id}")
    public ResponseEntity<SchoolResponse> updateSchool(@PathVariable UUID id, @RequestBody SchoolRequest schoolRequest) {
        SchoolDto schoolDto = schoolMapper.requestToDto(schoolRequest);
        schoolDto = schoolService.updateSchool(id, schoolDto);
        SchoolResponse schoolResponse = schoolMapper.dtoToResponse(schoolDto);
        return ResponseEntity.ok(schoolResponse); // Yanıt gövdesi olarak SchoolResponse objesi ve HTTP 200 döner
    }

    // Belirli bir okulu siler ve HTTP 204 (No Content) durum kodu döner
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable UUID id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build(); // Yanıt gövdesi olmadan HTTP 204 döner
    }
}