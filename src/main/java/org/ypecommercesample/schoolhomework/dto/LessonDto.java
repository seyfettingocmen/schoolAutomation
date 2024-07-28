package org.ypecommercesample.schoolhomework.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonDto {
    private UUID id;
    private String name;
    private UUID teacherId;
    private UUID classBranchId;
    private List<StudentLessonDto> studentLessonDtoList;

}