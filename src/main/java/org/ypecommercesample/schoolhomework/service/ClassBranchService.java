package org.ypecommercesample.schoolhomework.service;

import org.ypecommercesample.schoolhomework.dto.ClassBranchDto;
import org.ypecommercesample.schoolhomework.entity.ClassBranch;

import java.util.List;
import java.util.UUID;

public interface ClassBranchService {
    ClassBranchDto createClassBranch(ClassBranchDto classBranchDto);
    ClassBranchDto getClassBranchById(UUID id);
    List<ClassBranchDto> getAllClassBranches();
    ClassBranchDto updateClassBranch(UUID id, ClassBranchDto classBranchDto);
    void deleteClassBranch(UUID id);

    ClassBranch findClassBranchById(UUID id);
}