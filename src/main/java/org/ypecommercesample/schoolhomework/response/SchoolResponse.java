package org.ypecommercesample.schoolhomework.response;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolResponse {
    private UUID id;
    private String schoolName;

}