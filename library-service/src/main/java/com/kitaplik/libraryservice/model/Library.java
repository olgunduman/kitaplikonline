package com.kitaplik.libraryservice.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "library")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Library {

    @Id
    @Column(name = "library_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @ElementCollection
    private List<String> userBook = new ArrayList<>();
}
