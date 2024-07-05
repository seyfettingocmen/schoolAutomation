package org.ypecommercesample.schoolhomework.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassBranchRequest {
    private String branchName;
    private UUID classRoomId;
}