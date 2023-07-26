package com.example.resourcemanagerapp.dtos;


import com.example.resourcemanagerapp.additionalTypes.ResourceType;
import lombok.Getter;

@Getter
public class ResourceDTO {
    String name;
    Integer userId;
    ResourceType type;
    String metadata;
}
