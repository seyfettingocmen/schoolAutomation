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
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ClassBranch> classBranch;
}