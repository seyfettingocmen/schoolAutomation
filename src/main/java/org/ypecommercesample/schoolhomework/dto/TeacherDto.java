package org.ypecommercesample.schoolhomework.dto;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TeacherDto {
    private UUID id;
    private String fullName;
    private int age;
    private String tckn;
    private UUID lessonId;

}
