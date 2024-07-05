package org.ypecommercesample.schoolhomework.entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Identity {
    private String fullName;
    private int age;
    private String tckn;
}
