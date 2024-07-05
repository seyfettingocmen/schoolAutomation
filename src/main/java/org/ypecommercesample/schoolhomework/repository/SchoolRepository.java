package org.ypecommercesample.schoolhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ypecommercesample.schoolhomework.entity.School;

import java.util.UUID;

public interface SchoolRepository extends JpaRepository<School, UUID> {
}
