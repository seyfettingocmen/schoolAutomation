package org.ypecommercesample.schoolhomework.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentLessonRequest {
    private UUID studentId;
    private UUID lessonId;
    private Integer grade;
    private String semester;
}