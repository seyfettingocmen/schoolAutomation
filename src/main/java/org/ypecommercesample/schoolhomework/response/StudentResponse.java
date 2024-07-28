package org.ypecommercesample.schoolhomework.response;

import lombok.*;
import org.ypecommercesample.schoolhomework.dto.StudentLessonDto;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private UUID uuid;
    private String fullName;
    private int age;
    private String tckn;
    private List<StudentLessonDto> studentLessons;
}