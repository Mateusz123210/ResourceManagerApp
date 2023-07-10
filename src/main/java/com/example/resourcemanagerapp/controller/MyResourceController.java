package com.example.resourcemanagerapp.controller;


import com.example.resourcemanagerapp.service.MyResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyResourceController {

    private final MyResourceServiceImpl myResourceService;

    @Autowired
    public MyResourceController(MyResourceServiceImpl myResourceService) {
        this.myResourceService = myResourceService;
    }

    @PostMapping(value = "/resource/add")
    public ResponseEntity addResource(@RequestParam String name, @RequestParam Integer userId, @RequestParam String type,
                                      @RequestParam String metadata){




        return null;
    }

    @DeleteMapping(value = "/resource/delete")
    public ResponseEntity deleteResource(@RequestParam Integer id){




        return null;
    }

    @PutMapping(value = "/resource/edit-name")
    public ResponseEntity editResourceName(@RequestParam Integer id, @RequestParam String newName){



        return null;
    }

    @PutMapping(value = "/resource/edit-metadata")
    public ResponseEntity editResourceMetadata(@RequestParam Integer id, @RequestParam String metadataType,
                                               @RequestParam String metadata){




        return null;
    }
}
