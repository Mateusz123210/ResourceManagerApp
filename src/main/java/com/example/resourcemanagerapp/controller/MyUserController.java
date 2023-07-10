package com.example.resourcemanagerapp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyUserController {

    @PostMapping(value = "/user/add")
    public ResponseEntity addUser(){




        return null;
    }

    @DeleteMapping(value = "/user/delete")
    public ResponseEntity deleteUser(){




        return null;
    }

    @PutMapping(value = "/user/change-nick")
    public ResponseEntity changeUserNick(){




        return null;
    }



}
