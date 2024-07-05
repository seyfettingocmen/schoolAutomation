package org.ypecommercesample.schoolhomework.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ManagerResponse {
    private UUID id;
    private int authority;
    private String fullName;
    private int age;
    private String tckn;
    private SchoolResponse schoolResponse;
}
