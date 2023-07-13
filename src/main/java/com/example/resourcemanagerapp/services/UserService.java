package com.example.resourcemanagerapp.services;

import com.example.resourcemanagerapp.additionalTypes.UserType;
import com.example.resourcemanagerapp.models.ResourceEntity;
import com.example.resourcemanagerapp.models.UserEntity;
import com.example.resourcemanagerapp.repositories.ResourceRepository;
import com.example.resourcemanagerapp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository UserRepository;
    private final ResourceRepository resourceRepository;

    public void addUser(String nick, String name, String surname, UserType userType) {
        LocalDate currentDate = LocalDate.now();
        UserEntity userEntity = UserEntity.builder().nick(nick).name(name).surname(surname).type(userType).modificationTime(currentDate).
                creationTime(currentDate).build();
        UserRepository.save(userEntity);
    }

    @Transactional
    public void deleteUser(Integer id) {
        Optional<UserEntity> User = UserRepository.findById(id);
        if(User.isEmpty()){
            return;
        }
        List<ResourceEntity> userResourceEntities = resourceRepository.findAllByUserId(User.get());
        for(ResourceEntity resourceEntity : userResourceEntities){
            resourceRepository.deleteById(resourceEntity.getId());
        }
        UserRepository.deleteById(id);
    }

    @Transactional
    public void changeUserNick(Integer userId, String nick) {
        Optional<UserEntity> User = UserRepository.findById(userId);
        if(User.isEmpty()){
            return;
        }
        LocalDate currentDate = LocalDate.now();
        UserEntity userEntityOld = User.get();
        userEntityOld.setNick(nick);
        userEntityOld.setModificationTime(currentDate);
        UserRepository.save(userEntityOld);
    }
}
