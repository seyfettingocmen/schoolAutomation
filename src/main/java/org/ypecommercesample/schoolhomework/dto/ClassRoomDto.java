package org.ypecommercesample.schoolhomework.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassRoomDto {
    private UUID id;
    private String name;
    private SchoolDto schoolId;
    private List<ClassBranchDto> classBranchDtoList;
}