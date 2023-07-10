package com.example.resourcemanagerapp.model;


import com.example.resourcemanagerapp.additionalTypes.MyUserType;
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
@Table(name = "Users")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nick;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate creationTime;

    @Column(nullable = false)
    private LocalDate modificationTime;

    @Enumerated(EnumType.ORDINAL)
    private MyUserType type;
}
