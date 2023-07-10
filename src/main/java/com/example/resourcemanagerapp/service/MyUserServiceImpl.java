package com.example.resourcemanagerapp.service;

import com.example.resourcemanagerapp.additionalTypes.MyUserType;
import com.example.resourcemanagerapp.model.MyUser;
import com.example.resourcemanagerapp.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class MyUserServiceImpl implements MyUserService{

    private final MyUserRepository myUserRepository;

    @Autowired
    public MyUserServiceImpl(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public void addUser(String nick, String name, String surname, MyUserType userType) {
        LocalDate currentDate = LocalDate.now();
        MyUser myUser = MyUser.builder().nick(nick).name(name).surname(surname).type(userType).modificationTime(currentDate).
                creationTime(currentDate).build();
        myUserRepository.save(myUser);
    }
}
