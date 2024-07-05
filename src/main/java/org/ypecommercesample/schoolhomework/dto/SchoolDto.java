package org.ypecommercesample.schoolhomework.dto;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolDto {
    private UUID id;
    private String schoolName;
}