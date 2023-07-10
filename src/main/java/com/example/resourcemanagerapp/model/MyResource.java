package com.example.resourcemanagerapp.model;


import com.example.resourcemanagerapp.additionalTypes.MyResourceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Resources")
public class MyResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private LocalDate creationTime;

    @Column
    private LocalDate modificationTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MyUser userId;

    @Enumerated(EnumType.ORDINAL)
    private MyResourceType type;

    @Column(columnDefinition = "json")
    private String metadata;

}
