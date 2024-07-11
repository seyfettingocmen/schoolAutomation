package org.ypecommercesample.schoolhomework.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manager extends Identity {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
   private int authority;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "school_id", referencedColumnName = "id")
   private School school;
}