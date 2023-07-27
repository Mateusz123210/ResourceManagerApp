package com.example.resourcemanagerapp.dtos;

import com.example.resourcemanagerapp.additionalTypes.ResourceType;
import lombok.Getter;

@Getter
public class ResourceMetadataDTO {
    Integer resourceId;
    ResourceType metadataType;
    String metadata;
}
