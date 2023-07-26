package com.example.resourcemanagerapp.services;


import com.example.resourcemanagerapp.additionalTypes.UserType;
import com.example.resourcemanagerapp.dtos.UserDTO;
import com.example.resourcemanagerapp.dtos.UserNickDTO;
import com.example.resourcemanagerapp.exceptions.AuthorizedUserNotFoundException;
import com.example.resourcemanagerapp.exceptions.UserNotFoundException;
import com.example.resourcemanagerapp.exceptions.UserNotPermittedException;
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

    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;

    public ResponseEntity addUser(UserDTO userDTO) {
        String nick = userDTO.getNick();
        String name = userDTO.getName();
        String surname = userDTO.getSurname();
        UserType type = userDTO.getType();

        UserApiParamsValidator.validateAddUserParameters(nick, name, surname, type);

        UserEntity userEntity = UserEntity.builder()
                .nick(nick)
                .name(name)
                .surname(surname)
                .type(type)
                .build();
        LocalDateTime currentDateTime = LocalDateTime.now();

        userEntity.setModificationTime(currentDateTime);
        userEntity.setCreationTime(currentDateTime);
        userRepository.save(userEntity);

        return ResponseEntity.ok("User was added!");
    }

    @Transactional
    public ResponseEntity deleteUser(Integer userId, Integer authorizedUserId) {
        UserApiParamsValidator.validateUserId(userId, authorizedUserId);

        UserEntity authorizedUser = userRepository.findById(authorizedUserId).
                orElseThrow(() -> new AuthorizedUserNotFoundException(
                        "User was not deleted, because authorized user does not exist!"));

        if(authorizedUser.getType() == UserType.DEFAULT){
            throw new UserNotPermittedException("User is not permitted to delete users!");
        }

        UserEntity user = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException("User was not deleted, because does not exist!"));
        List<ResourceEntity> userResourceEntities = resourceRepository.findAllByUserId(user);

        for(ResourceEntity resourceEntity : userResourceEntities){
            resourceRepository.deleteById(resourceEntity.getId());
        }
        userRepository.deleteById(userId);

        return ResponseEntity.ok("User was deleted!");
    }

    @Transactional
    public ResponseEntity updateUserNick(UserNickDTO userNickDTO) {
        Integer userId = userNickDTO.getUserId();
        String newNick = userNickDTO.getNewNick();

        UserApiParamsValidator.checkUpdateUserNickParameters(userId, newNick);

        UserEntity user = userRepository.findById(userNickDTO.getUserId()).orElseThrow(() ->
                new UserNotFoundException("Nick was not changed. User with this id does not exist!"));
        LocalDateTime currentDateTime = LocalDateTime.now();

        user.setNick(newNick);
        user.setModificationTime(currentDateTime);
        userRepository.save(user);

        return ResponseEntity.ok("User nick was changed!");
    }
}
