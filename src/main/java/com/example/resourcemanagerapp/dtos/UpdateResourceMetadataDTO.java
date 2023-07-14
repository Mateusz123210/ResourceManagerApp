package com.example.resourcemanagerapp.dtos;

import com.example.resourcemanagerapp.additionalTypes.ResourceType;
import lombok.Getter;

@Getter
public class UpdateResourceMetadataDTO {
    Integer id;
    ResourceType metadataType;
    String metadata;
}
