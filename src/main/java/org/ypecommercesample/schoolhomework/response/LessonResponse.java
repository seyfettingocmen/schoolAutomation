package org.ypecommercesample.schoolhomework.response;

import lombok.*;
import org.ypecommercesample.schoolhomework.dto.StudentLessonDto;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonResponse {
    private UUID id;
    private String name;
    private UUID teacherId;
    private UUID classBranchId;
    private List<StudentLessonDto> studentLessonDtoList;
}