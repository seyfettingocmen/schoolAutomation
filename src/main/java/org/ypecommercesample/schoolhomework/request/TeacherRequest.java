package org.ypecommercesample.schoolhomework.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {
    private String fullName;
    private int age;
    private String tckn;
    private UUID lessonId;

}
