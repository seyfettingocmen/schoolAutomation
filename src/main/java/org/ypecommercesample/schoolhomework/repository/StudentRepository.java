package org.ypecommercesample.schoolhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ypecommercesample.schoolhomework.entity.Student;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

}
