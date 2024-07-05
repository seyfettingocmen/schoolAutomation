package org.ypecommercesample.schoolhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ypecommercesample.schoolhomework.entity.Lesson;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {
}
