package com.example.resourcemanagerapp.service;


import com.example.resourcemanagerapp.additionalTypes.MyResourceType;
import com.example.resourcemanagerapp.model.MyResource;
import com.example.resourcemanagerapp.model.MyUser;
import com.example.resourcemanagerapp.repository.MyResourceRepository;
import com.example.resourcemanagerapp.repository.MyUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MyResourceServiceImpl implements MyResourceService{

    private final MyResourceRepository myResourceRepository;
    private final MyUserRepository myUserRepository;

    @Autowired
    public MyResourceServiceImpl(MyResourceRepository myResourceRepository, MyUserRepository myUserRepository) {
        this.myResourceRepository = myResourceRepository;
        this.myUserRepository = myUserRepository;
    }

    @Override
    public void deleteResource(Integer id) {
        myResourceRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void addResource(String name, Integer userId, MyResourceType type, String metadata) {
        Optional<MyUser> myUser = myUserRepository.findById(userId);
        if(myUser.isEmpty()) return;
        LocalDate currentDate = LocalDate.now();
        MyResource resource = MyResource.builder().name(name).userId(myUser.get()).type(type).
                metadata(metadata).creationTime(currentDate).modificationTime(currentDate).build();
        myResourceRepository.save(resource);
    }

    @Transactional
    @Override
    public void changeResourceName(Integer id, String newName) {
        Optional<MyResource> myResource = myResourceRepository.findById(id);
        if(myResource.isEmpty()) return;
        MyResource myResource1 = myResource.get();
        myResource1.setName(newName);
        myResource1.setModificationTime(LocalDate.now());
        myResourceRepository.save(myResource1);
    }

    @Transactional
    @Override
    public void changeResourceMetadata(Integer id, MyResourceType type, String metadata) {
        Optional<MyResource> myResource = myResourceRepository.findById(id);
        if(myResource.isEmpty()) return;
        MyResource myResource1 = myResource.get();
        myResource1.setType(type);
        myResource1.setMetadata(metadata);
        myResource1.setModificationTime(LocalDate.now());
        myResourceRepository.save(myResource1);
    }
}
