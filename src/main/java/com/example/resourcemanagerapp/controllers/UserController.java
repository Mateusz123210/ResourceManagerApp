package com.example.resourcemanagerapp.controllers;


import com.example.resourcemanagerapp.additionalTypes.EnumChecker;
import com.example.resourcemanagerapp.additionalTypes.UserType;
import com.example.resourcemanagerapp.mappers.AddUserDTO;
import com.example.resourcemanagerapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/user")
    public ResponseEntity addUser(@RequestBody AddUserDTO addUserDTO){
        String nick = addUserDTO.getNick();
        String name = addUserDTO.getName();
        String surname = addUserDTO.getSurname();
        String type = addUserDTO.getType();
        if(nick.length() == 0 || name.length() == 0 || surname.length() == 0 || type.length() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        if(!checkNick(nick))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        if(!checkNameOrSurname(name) || !checkNameOrSurname(surname))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        UserType userType = EnumChecker.containsUserType(type);
        if( userType == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        userService.addUser(nick, name, surname, userType);
        return ResponseEntity.ok("");
    }

    @DeleteMapping(value = "/user")
    public ResponseEntity deleteUser(@RequestParam Integer id){
        if(id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        }
        userService.deleteUser(id);
        return ResponseEntity.ok("");
    }

    @PutMapping(value = "/user/edit-nick")
    public ResponseEntity editUserNick(@RequestParam Integer id, @RequestParam String newNick){
        if(id <= 0 || newNick.length() == 0 || !checkNick(newNick)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        }
        userService.changeUserNick(id, newNick);
        return ResponseEntity.ok("");
    }

    private Boolean checkNick(String nick){
        for(int i = 0; i < nick.length(); i++){
            if(!Character.isLetter(nick.charAt(i)) && !Character.isDigit(nick.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private Boolean checkNameOrSurname(String str){
        for(int i = 0; i < str.length(); i++){
            if(!Character.isLetter(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
