package org.ypecommercesample.schoolhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ypecommercesample.schoolhomework.entity.StudentLesson;

import java.util.List;
import java.util.UUID;

public interface StudentLessonRepository extends JpaRepository<StudentLesson, UUID> {
    List<StudentLesson> findByStudentId(UUID studentId);
    List<StudentLesson> findByLessonId(UUID lessonId);
}
