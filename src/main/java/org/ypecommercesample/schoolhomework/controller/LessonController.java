package org.ypecommercesample.schoolhomework.controller;

import org.ypecommercesample.schoolhomework.dto.LessonDto;
import org.ypecommercesample.schoolhomework.request.LessonRequest;
import org.ypecommercesample.schoolhomework.response.LessonResponse;
import org.ypecommercesample.schoolhomework.service.LessonService;
import org.ypecommercesample.schoolhomework.mapper.LessonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private LessonMapper lessonMapper;

    // HTTP POST isteği ile yeni bir lesson oluşturur ve HTTP 200 (OK) durum kodu ile birlikte oluşturulan lesson'un bilgilerini döner
    @PostMapping
    public ResponseEntity<LessonResponse> createLesson(@RequestBody LessonRequest lessonRequest) {
        LessonDto lessonDto = lessonMapper.requestToDto(lessonRequest);
        lessonDto = lessonService.createLesson(lessonDto);
        LessonResponse lessonResponse = lessonMapper.dtoToResponse(lessonDto);
        return ResponseEntity.ok(lessonResponse); // Yanıt gövdesi olarak LessonResponse objesi ve HTTP 200 döner
    }

    // Belirli bir lesson'un bilgilerini HTTP 200 (OK) durum kodu ile birlikte döner
    @GetMapping("/{id}")
    public ResponseEntity<LessonResponse> getLessonById(@PathVariable UUID id) {
        LessonDto lessonDto = lessonService.getLessonById(id);
        LessonResponse lessonResponse = lessonMapper.dtoToResponse(lessonDto);
        return ResponseEntity.ok(lessonResponse); // Yanıt gövdesi olarak LessonResponse objesi ve HTTP 200 döner
    }

    // Tüm lesson'ları HTTP 200 (OK) durum kodu ile birlikte döner
    @GetMapping
    public ResponseEntity<List<LessonResponse>> getAllLessons() {
        List<LessonDto> lessonDtos = lessonService.getAllLessons();
        List<LessonResponse> lessonResponses = lessonDtos.stream().map(lessonMapper::dtoToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(lessonResponses); // Yanıt gövdesi olarak LessonResponse objeleri listesi ve HTTP 200 döner
    }

    // Belirli bir lesson'u günceller ve HTTP 200 (OK) durum kodu ile birlikte güncellenmiş lesson'un bilgilerini döner
    @PutMapping("/{id}")
    public ResponseEntity<LessonResponse> updateLesson(@PathVariable UUID id, @RequestBody LessonRequest lessonRequest) {
        LessonDto lessonDto = lessonMapper.requestToDto(lessonRequest);
        lessonDto = lessonService.updateLesson(id, lessonDto);
        LessonResponse lessonResponse = lessonMapper.dtoToResponse(lessonDto);
        return ResponseEntity.ok(lessonResponse); // Yanıt gövdesi olarak LessonResponse objesi ve HTTP 200 döner
    }

    // Belirli bir lesson'u siler ve HTTP 204 (No Content) durum kodu döner
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable UUID id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build(); // Yanıt gövdesi olmadan HTTP 204 döner
    }
}