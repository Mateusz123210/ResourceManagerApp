package com.example.resourcemanagerapp.controllers;


import com.example.resourcemanagerapp.dtos.AddUserDTO;
import com.example.resourcemanagerapp.dtos.UpdateUserNickDTO;
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
    public ResponseEntity addUser(@RequestBody AddUserDTO addUserDTO){
        return userService.addUser(addUserDTO);
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestParam Integer id){
        return userService.deleteUser(id);
    }

    @PutMapping(value = "/update-nick")
    public ResponseEntity updateUserNick(@RequestBody UpdateUserNickDTO updateUserNickDTO){
        return userService.updateUserNick(updateUserNickDTO);
    }
}
