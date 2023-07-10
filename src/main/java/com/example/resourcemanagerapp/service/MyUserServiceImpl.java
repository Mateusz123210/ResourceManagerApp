package com.example.resourcemanagerapp.service;

import com.example.resourcemanagerapp.additionalTypes.MyUserType;
import com.example.resourcemanagerapp.model.MyUser;
import com.example.resourcemanagerapp.repository.MyUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


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

    @Override
    public void deleteUser(Integer id) {
        myUserRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void changeUserNick(Integer userId, String nick) {
        Optional<MyUser> myUser = myUserRepository.findById(userId);
        if(myUser.isEmpty()) return;
        MyUser myUserOld = myUser.get();
        myUserOld.setNick(nick);
        myUserRepository.save(myUserOld);
    }
}
