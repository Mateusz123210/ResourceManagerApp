package com.example.resourcemanagerapp.service;

import com.example.resourcemanagerapp.additionalTypes.ResourceType;

public interface ResourceService {

    public void deleteResource(Integer id);

    public void addResource(String name, Integer userId, ResourceType type, String metadata);

    public void changeResourceName(Integer id, String newName);

    public void changeResourceMetadata(Integer id, ResourceType type, String metadata);
}
