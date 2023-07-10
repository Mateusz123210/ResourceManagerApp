package com.example.resourcemanagerapp.service;


import com.example.resourcemanagerapp.repository.MyResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyResourceServiceImpl implements MyResourceService{

    private final MyResourceRepository repository;

    @Autowired
    public MyResourceServiceImpl(MyResourceRepository repository) {
        this.repository = repository;
    }






}
