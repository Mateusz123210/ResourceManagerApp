package com.example.resourcemanagerapp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyUserController {

    @PostMapping(value = "/user/add")
    public ResponseEntity addUser(@RequestParam  String nick, @RequestParam String name, @RequestParam String surname,
                                  @RequestParam String userType){




        return null;
    }

    @DeleteMapping(value = "/user/delete")
    public ResponseEntity deleteUser(@RequestParam Integer id){





        return null;
    }

    @PutMapping(value = "/user/change-nick")
    public ResponseEntity changeUserNick(@RequestParam Integer id, @RequestParam String newNick){





        return null;
    }



}
