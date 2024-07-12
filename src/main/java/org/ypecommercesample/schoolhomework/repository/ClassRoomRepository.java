package org.ypecommercesample.schoolhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ypecommercesample.schoolhomework.entity.ClassRoom;

import java.util.UUID;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, UUID> {

}
