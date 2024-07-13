package org.ypecommercesample.schoolhomework.service;

import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
import org.ypecommercesample.schoolhomework.entity.ClassRoom;

import java.util.List;
import java.util.UUID;

public interface ClassRoomService {
    ClassRoomDto createClassRoom(ClassRoomDto classRoomDto);
    ClassRoomDto getClassRoomById(UUID id);
    List<ClassRoomDto> getAllClassRooms();
    ClassRoomDto updateClassRoom(UUID id, ClassRoomDto classRoomDto);
    void deleteClassRoom(UUID id);

}