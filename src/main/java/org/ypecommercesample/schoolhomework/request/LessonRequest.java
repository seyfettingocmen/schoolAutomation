package org.ypecommercesample.schoolhomework.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonRequest {
    private String name;
    private UUID studentId;
    private UUID classBranchId;

}