package com.example.resourcemanagerapp.service;


import com.example.resourcemanagerapp.additionalTypes.MyResourceType;
import com.example.resourcemanagerapp.repository.MyResourceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyResourceServiceImpl implements MyResourceService{

    private final MyResourceRepository myResourceRepository;

    @Autowired
    public MyResourceServiceImpl(MyResourceRepository myResourceRepository) {
        this.myResourceRepository = myResourceRepository;
    }

    @Override
    public void deleteResource(Integer id) {
        myResourceRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void addResource(String name, Integer userId, MyResourceType type, String metadata) {
        



    }

    @Transactional
    @Override
    public void changeResourceName(Integer id, String newName) {



    }

    @Transactional
    @Override
    public void changeResourceMetadata(Integer id, MyResourceType type, String metadata) {

    }
}
