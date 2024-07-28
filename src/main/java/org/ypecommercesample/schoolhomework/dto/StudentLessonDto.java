package org.ypecommercesample.schoolhomework.dto;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentLessonDto {
    private UUID id;
    private UUID studentId;
    private UUID lessonId;
    private int grade;
    private String semester;
}
