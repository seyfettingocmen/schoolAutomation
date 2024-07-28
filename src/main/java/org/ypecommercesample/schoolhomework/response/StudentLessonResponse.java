package org.ypecommercesample.schoolhomework.response;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentLessonResponse {
    private UUID id;
    private UUID studentId;
    private UUID lessonId;
    private int grade;
    private String semester;
}
