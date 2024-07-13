package org.ypecommercesample.schoolhomework.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassBranchDto {
    private UUID id;
    private String branchName;
    private UUID classRoomId;
    private List<LessonDto> lessonDtoList;
}