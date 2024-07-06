package org.ypecommercesample.schoolhomework.response;

import lombok.*;
import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
import org.ypecommercesample.schoolhomework.dto.ManagerDto;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolResponse {
    private UUID id;
    private String schoolName;
    private List<ManagerDto> managerDtoList;
    private List<ClassRoomDto> classRoomDtoList;
}