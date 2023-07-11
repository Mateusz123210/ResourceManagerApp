package com.example.resourcemanagerapp.service;

import com.example.resourcemanagerapp.additionalTypes.MyUserType;
import com.example.resourcemanagerapp.model.MyResource;
import com.example.resourcemanagerapp.model.MyUser;
import com.example.resourcemanagerapp.repository.MyResourceRepository;
import com.example.resourcemanagerapp.repository.MyUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class MyUserServiceImpl implements MyUserService{

    private final MyUserRepository myUserRepository;
    private final MyResourceRepository myResourceRepository;

    @Autowired
    public MyUserServiceImpl(MyUserRepository myUserRepository, MyResourceRepository myResourceRepository) {
        this.myUserRepository = myUserRepository;
        this.myResourceRepository = myResourceRepository;
    }

    @Override
    public void addUser(String nick, String name, String surname, MyUserType userType) {
        LocalDate currentDate = LocalDate.now();
        MyUser myUser = MyUser.builder().nick(nick).name(name).surname(surname).type(userType).modificationTime(currentDate).
                creationTime(currentDate).build();
        myUserRepository.save(myUser);
    }

    @Transactional
    @Override
    public void deleteUser(Integer id) {
        Optional<MyUser> myUser = myUserRepository.findById(id);
        if(myUser.isEmpty()) return;
        List<MyResource> userResources = myResourceRepository.findAllByUserId(myUser.get());
        for(MyResource myResource: userResources)
            myResourceRepository.deleteById(myResource.getId());
        myUserRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void changeUserNick(Integer userId, String nick) {
        Optional<MyUser> myUser = myUserRepository.findById(userId);
        if(myUser.isEmpty()) return;
        LocalDate currentDate = LocalDate.now();
        MyUser myUserOld = myUser.get();
        myUserOld.setNick(nick);
        myUserOld.setModificationTime(currentDate);
        myUserRepository.save(myUserOld);
    }
}
