package org.ypecommercesample.schoolhomework.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String branchName;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;

    @OneToMany(mappedBy = "classBranch")
    private List<Lesson> lessonList;
}