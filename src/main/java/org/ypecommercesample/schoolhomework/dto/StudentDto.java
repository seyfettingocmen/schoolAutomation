package org.ypecommercesample.schoolhomework.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StudentDto {
    private UUID uuid;
    private String fullName;
    private int age;
    private String tckn;
    private List<StudentLessonDto> studentLessons;
}