package com.example.resourcemanagerapp.controllers;


import com.example.resourcemanagerapp.dtos.UserDTO;
import com.example.resourcemanagerapp.dtos.UserNickDTO;
import com.example.resourcemanagerapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestParam Integer userId, @RequestParam Integer authorizedUserId){
        return userService.deleteUser(userId, authorizedUserId);
    }

    @PutMapping(value = "/update-nick")
    public ResponseEntity updateUserNick(@RequestBody UserNickDTO userNickDTO){
        return userService.updateUserNick(userNickDTO);
    }
}
