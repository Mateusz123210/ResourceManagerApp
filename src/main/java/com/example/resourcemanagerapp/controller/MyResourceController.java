package com.example.resourcemanagerapp.controller;


import com.example.resourcemanagerapp.service.MyResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyResourceController {

    private final MyResourceServiceImpl myResourceService;

    @Autowired
    public MyResourceController(MyResourceServiceImpl myResourceService) {
        this.myResourceService = myResourceService;
    }

    @PostMapping(value = "/resource/add")
    public ResponseEntity addResource(){
        System.out.println("Resource added");




        return null;
    }

    @DeleteMapping(value = "/resource/delete")
    public ResponseEntity deleteResource(){




        return null;
    }

    @PutMapping(value = "/resource/edit-name")
    public ResponseEntity editResourceName(){



        return null;
    }

    @PutMapping(value = "/resource/edit-metadata")
    public ResponseEntity editResourceMetadata(){




        return null;
    }
}
