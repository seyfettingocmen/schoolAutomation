package org.ypecommercesample.schoolhomework.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerRequest {
    private String fullName;
    private int age;
    private String tckn;
    private int authority;
    private UUID schoolId;

}
