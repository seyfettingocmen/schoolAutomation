package org.ypecommercesample.schoolhomework.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String schoolName;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "school-classroom")
    private List<ClassRoom> classRoomList;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "school-manager")
    private List<Manager> managerList;
}