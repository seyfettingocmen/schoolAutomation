package org.ypecommercesample.schoolhomework.response;

import lombok.*;
import org.ypecommercesample.schoolhomework.dto.ClassRoomDto;
import org.ypecommercesample.schoolhomework.dto.LessonDto;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassBranchResponse {
    private UUID id;
    private String branchName;
    private UUID classRoomId;
    private List<LessonDto> lessonDtoList;
}