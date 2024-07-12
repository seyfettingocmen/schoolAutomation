package org.ypecommercesample.schoolhomework.controller;

import org.ypecommercesample.schoolhomework.dto.TeacherDto;
import org.ypecommercesample.schoolhomework.request.TeacherRequest;
import org.ypecommercesample.schoolhomework.response.TeacherResponse;
import org.ypecommercesample.schoolhomework.service.TeacherService;
import org.ypecommercesample.schoolhomework.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("teachers") // Bu sınıftaki tüm yöntemleri "/teachers" temel URL'sine eşler
public class TeacherController {

    @Autowired // TeacherService örneğini otomatik olarak enjekte eder
    private TeacherService teacherService;

    @Autowired // TeacherMapper örneğini otomatik olarak enjekte eder
    private TeacherMapper teacherMapper;

    // **Yeni bir Öğretmen oluşturur**
    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody TeacherRequest teacherRequest) {
        // 1. İsteği bir Teacher DTO'ya mapper kullanarak eşler
        TeacherDto teacherDto = teacherMapper.requestToDto(teacherRequest);
        // 2. Öğretmeni oluşturmak ve kaydetmek için TeacherService'i çağırır
        teacherDto = teacherService.createTeacher(teacherDto);
        // 3. Oluşturulan Teacher DTO'yu bir TeacherResponse'a mapper kullanarak eşler
        TeacherResponse teacherResponse = teacherMapper.dtoToResponse(teacherDto);
        // 4. TeacherResponse nesnesi ile başarılı bir HTTP yanıtı döner (kod 200 OK)
        return ResponseEntity.ok(teacherResponse);
    }

    // **ID'ye göre bir Öğretmen alır**
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable UUID id) {
        // 1. TeacherService'i çağırarak ID'ye göre bir Öğretmen bulur
        TeacherDto teacherDto = teacherService.getTeacherById(id);
        // 2. Alınan Teacher DTO'yu bir TeacherResponse'a mapper kullanarak eşler
        TeacherResponse teacherResponse = teacherMapper.dtoToResponse(teacherDto);
        // 3. TeacherResponse nesnesi ile başarılı bir HTTP yanıtı döner (kod 200 OK)
        return ResponseEntity.ok(teacherResponse);
    }

    // **Tüm Öğretmenleri alır**
    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
        // 1. TeacherService'i çağırarak tüm Öğretmen nesnelerini alır
        List<TeacherDto> teacherDtos = teacherService.getAllTeachers();
        // 2. Java Streams kullanarak her Teacher DTO'yu mapper kullanarak bir TeacherResponse nesnesine eşler
        List<TeacherResponse> teacherResponses = teacherDtos.stream().map(teacherMapper::dtoToResponse).collect(Collectors.toList());
        // 3. TeacherResponse nesneleri ile başarılı bir HTTP yanıtı döner (kod 200 OK)
        return ResponseEntity.ok(teacherResponses);
    }

    // **Bir Öğretmeni günceller**
    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> updateTeacher(@PathVariable UUID id, @RequestBody TeacherRequest teacherRequest) {
        // 1. İsteği bir Teacher DTO'ya mapper kullanarak eşler
        TeacherDto teacherDto = teacherMapper.requestToDto(teacherRequest);
        // 2. Verilen ID'ye sahip Öğretmeni ve DTO'dan gelen verileri güncellemek için TeacherService'i çağırır
        teacherDto = teacherService.updateTeacher(id, teacherDto);
        // 3. Güncellenen Teacher DTO'yu bir TeacherResponse'a mapper kullanarak eşler
        TeacherResponse teacherResponse = teacherMapper.dtoToResponse(teacherDto);
        // 4. TeacherResponse nesnesi ile başarılı bir HTTP yanıtı döner (kod 200 OK)
        return ResponseEntity.ok(teacherResponse);
    }

    // **Bir Öğretmeni siler**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable UUID id) {
        // 1. Verilen ID'ye sahip Öğretmeni silmek için TeacherService'i çağırır
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}