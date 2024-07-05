package org.ypecommercesample.schoolhomework.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassRoomRequest {
    private String name;
    private UUID schoolId;
}