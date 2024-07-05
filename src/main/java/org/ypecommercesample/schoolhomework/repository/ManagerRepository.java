package org.ypecommercesample.schoolhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ypecommercesample.schoolhomework.entity.Manager;

import java.util.UUID;

public interface ManagerRepository extends JpaRepository<Manager, UUID> {
}
