package com.example.resourcemanagerapp.service;

import com.example.resourcemanagerapp.additionalTypes.MyResourceType;

public interface MyResourceService {


    public void deleteResource(Integer id);
    public void addResource(String name, Integer userId, MyResourceType type, String metadata);
    public void changeResourceName(Integer id, String newName);
    public void changeResourceMetadata(Integer id, MyResourceType type, String metadata);
}
