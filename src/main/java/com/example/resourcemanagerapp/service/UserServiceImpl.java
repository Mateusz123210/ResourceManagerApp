package com.example.resourcemanagerapp.service;

import com.example.resourcemanagerapp.additionalTypes.UserType;
import com.example.resourcemanagerapp.model.Resource;
import com.example.resourcemanagerapp.model.User;
import com.example.resourcemanagerapp.repository.ResourceRepository;
import com.example.resourcemanagerapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository UserRepository;
    private final ResourceRepository resourceRepository;

    @Override
    public void addUser(String nick, String name, String surname, UserType userType) {
        LocalDate currentDate = LocalDate.now();
        User user = User.builder().nick(nick).name(name).surname(surname).type(userType).modificationTime(currentDate).
                creationTime(currentDate).build();
        UserRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Integer id) {
        Optional<User> User = UserRepository.findById(id);
        if(User.isEmpty()){
            return;
        }
        List<Resource> userResources = resourceRepository.findAllByUserId(User.get());
        for(Resource resource : userResources){
            resourceRepository.deleteById(resource.getId());
        }
        UserRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void changeUserNick(Integer userId, String nick) {
        Optional<User> User = UserRepository.findById(userId);
        if(User.isEmpty()){
            return;
        }
        LocalDate currentDate = LocalDate.now();
        User UserOld = User.get();
        UserOld.setNick(nick);
        UserOld.setModificationTime(currentDate);
        UserRepository.save(UserOld);
    }
}
