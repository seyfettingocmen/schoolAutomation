package org.ypecommercesample.schoolhomework.response;

import lombok.*;
import org.ypecommercesample.schoolhomework.dto.LessonDto;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TeacherResponse {
    private UUID id;
    private String fullName;
    private int age;
    private String tckn;
    private LessonDto lessonDto;

}