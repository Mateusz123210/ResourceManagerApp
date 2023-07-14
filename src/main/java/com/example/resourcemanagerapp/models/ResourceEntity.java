package com.example.resourcemanagerapp.models;


import com.example.resourcemanagerapp.additionalTypes.ResourceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Resources")
public class ResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    @Column(nullable = false)
    private LocalDateTime modificationTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity userId;

    @Enumerated(EnumType.ORDINAL)
    private ResourceType type;

    @Column(nullable = false)
    private String metadata;
}
