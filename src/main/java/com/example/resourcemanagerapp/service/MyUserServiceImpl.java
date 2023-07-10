package com.example.resourcemanagerapp.service;

import com.example.resourcemanagerapp.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MyUserServiceImpl implements MyUserService{

    private final MyUserRepository repository;

    @Autowired
    public MyUserServiceImpl(MyUserRepository repository) {
        this.repository = repository;
    }



}
