package com.example.resourcemanagerapp.model;


import com.example.resourcemanagerapp.additionalTypes.ResourceType;
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
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate creationTime;

    @Column(nullable = false)
    private LocalDate modificationTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User userId;

    @Enumerated(EnumType.ORDINAL)
    private ResourceType type;

//    @Column(columnDefinition = "json", nullable = false)

//    @Type(type="jsonb")
//    @Column(columnDefinition = "jsonb", nullable = false)
    @Column(nullable = false)
    private String metadata;

}
