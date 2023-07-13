package com.example.resourcemanagerapp.mappers;


import lombok.Getter;

@Getter
public class AddResourceDTO {
    String name;
    Integer userId;
    String type;
    String metadata;
}
