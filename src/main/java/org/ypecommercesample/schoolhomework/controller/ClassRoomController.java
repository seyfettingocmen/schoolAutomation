package org.ypecommercesample.schoolhomework.controller;

import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
import org.ypecommercesample.schoolhomework.request.ClassRoomRequest;
import org.ypecommercesample.schoolhomework.response.ClassRoomResponse;
import org.ypecommercesample.schoolhomework.service.ClassRoomService;
import org.ypecommercesample.schoolhomework.mapper.ClassRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("classrooms")
public class ClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private ClassRoomMapper classRoomMapper;

    @PostMapping
    public ResponseEntity<ClassRoomResponse> createClassRoom(@RequestBody ClassRoomRequest classRoomRequest) {
        ClassRoomDto classRoomDto = classRoomMapper.requestToDto(classRoomRequest);
        classRoomDto = classRoomService.createClassRoom(classRoomDto);
        ClassRoomResponse classRoomResponse = classRoomMapper.dtoToResponse(classRoomDto);
        return ResponseEntity.ok(classRoomResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoomResponse> getClassRoomById(@PathVariable UUID id) {
        ClassRoomDto classRoomDto = classRoomService.getClassRoomById(id);
        ClassRoomResponse classRoomResponse = classRoomMapper.dtoToResponse(classRoomDto);
        return ResponseEntity.ok(classRoomResponse);
    }

    @GetMapping
    public ResponseEntity<List<ClassRoomResponse>> getAllClassRooms() {
        List<ClassRoomDto> classRoomDtos = classRoomService.getAllClassRooms();
        List<ClassRoomResponse> classRoomResponses = classRoomDtos.stream().map(classRoomMapper::dtoToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(classRoomResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoomResponse> updateClassRoom(@PathVariable UUID id, @RequestBody ClassRoomRequest classRoomRequest) {
        ClassRoomDto classRoomDto = classRoomMapper.requestToDto(classRoomRequest);
        classRoomDto = classRoomService.updateClassRoom(id, classRoomDto);
        ClassRoomResponse classRoomResponse = classRoomMapper.dtoToResponse(classRoomDto);
        return ResponseEntity.ok(classRoomResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassRoom(@PathVariable UUID id) {
        classRoomService.deleteClassRoom(id);
        return ResponseEntity.noContent().build();
    }
}