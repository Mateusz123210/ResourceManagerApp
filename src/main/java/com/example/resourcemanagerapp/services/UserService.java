package com.example.resourcemanagerapp.services;

import com.example.resourcemanagerapp.additionalTypes.UserType;
import com.example.resourcemanagerapp.dtos.AddUserDTO;
import com.example.resourcemanagerapp.dtos.UpdateUserNickDTO;
import com.example.resourcemanagerapp.exceptions.ApplicationException;
import com.example.resourcemanagerapp.models.ResourceEntity;
import com.example.resourcemanagerapp.models.UserEntity;
import com.example.resourcemanagerapp.repositories.ResourceRepository;
import com.example.resourcemanagerapp.repositories.UserRepository;
import com.example.resourcemanagerapp.validators.UserApiParamsValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository UserRepository;
    private final ResourceRepository resourceRepository;

    public ResponseEntity addUser(AddUserDTO addUserDTO) {
        String nick = addUserDTO.getNick();
        String name = addUserDTO.getName();
        String surname = addUserDTO.getSurname();
        UserType type = addUserDTO.getType();
        UserApiParamsValidator.validateAddUserParameters(nick, name, surname, type);
        UserEntity userEntity = UserEntity.builder().nick(nick).name(name).surname(surname).type(type)
                .build();
        LocalDateTime currentDateTime = LocalDateTime.now();
        userEntity.setModificationTime(currentDateTime);
        userEntity.setCreationTime(currentDateTime);
        UserRepository.save(userEntity);
        return ResponseEntity.ok("User was added!");
    }

    @Transactional
    public ResponseEntity deleteUser(Integer id) {
        UserApiParamsValidator.validateUserId(id);
        UserEntity user = UserRepository.findById(id).
                orElseThrow(() -> new ApplicationException("User was not deleted, because does not exist!"));
        List<ResourceEntity> userResourceEntities = resourceRepository.findAllByUserId(user);
        for(ResourceEntity resourceEntity : userResourceEntities){
            resourceRepository.deleteById(resourceEntity.getId());
        }
        UserRepository.deleteById(id);
        return ResponseEntity.ok("User was deleted!");
    }

    @Transactional
    public ResponseEntity updateUserNick(UpdateUserNickDTO updateUserNickDTO) {
        UserApiParamsValidator.checkUpdateUserNickParameters(updateUserNickDTO.getId(), updateUserNickDTO.getNewNick());
        UserEntity user = UserRepository.findById(updateUserNickDTO.getId()).orElseThrow(() ->
                new ApplicationException("Nick was not changed. User with this id does not exist!"));
        LocalDateTime currentDateTime = LocalDateTime.now();
        user.setNick(updateUserNickDTO.getNewNick());
        user.setModificationTime(currentDateTime);
        UserRepository.save(user);
        return ResponseEntity.ok("User nick was changed!");
    }
}
