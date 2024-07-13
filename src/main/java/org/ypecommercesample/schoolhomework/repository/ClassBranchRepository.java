package org.ypecommercesample.schoolhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ypecommercesample.schoolhomework.entity.ClassBranch;

import java.util.List;
import java.util.UUID;

public interface ClassBranchRepository extends JpaRepository<ClassBranch, UUID> {
    // List<ClassBranch> findAllByClassRoom_Id (UUID id);

}
