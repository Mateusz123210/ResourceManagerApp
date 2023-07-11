package com.example.resourcemanagerapp.controller;


import com.example.resourcemanagerapp.additionalTypes.EnumChecker;
import com.example.resourcemanagerapp.additionalTypes.MyResourceType;
import com.example.resourcemanagerapp.service.MyResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
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
        if(name.length() == 0 || userId <= 0 || type.length() == 0 || metadata.length() == 0 ||
                !checkName(name))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        MyResourceType myResourceType = EnumChecker.containsResourceType(type);
        if( myResourceType == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        try{
            new JSONObject(metadata);
        }catch(JSONException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        }
        myResourceService.addResource(name, userId, myResourceType, metadata);
        return ResponseEntity.ok("");
    }

    @DeleteMapping(value = "/resource/delete")
    public ResponseEntity deleteResource(@RequestParam Integer id){
        if(id <= 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        myResourceService.deleteResource(id);
        return ResponseEntity.ok("");
    }

    @PutMapping(value = "/resource/edit-name")
    public ResponseEntity editResourceName(@RequestParam Integer id, @RequestParam String newName){
        if(id <= 0 || !checkName(newName))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        myResourceService.changeResourceName(id, newName);

        return ResponseEntity.ok("");
    }

    @PutMapping(value = "/resource/edit-metadata")
    public ResponseEntity editResourceMetadata(@RequestParam Integer id, @RequestParam String metadataType,
                                               @RequestParam String metadata){
        if(id <= 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        MyResourceType myResourceType = EnumChecker.containsResourceType(metadataType);
        if( myResourceType == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        try{
            new JSONObject(metadata);
        }catch(JSONException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        }
        myResourceService.changeResourceMetadata(id, myResourceType, metadata);

        return ResponseEntity.ok("");
    }

    private Boolean checkName(String name){
        for(int i = 0; i < name.length(); i++){
            if(!Character.isLetter(name.charAt(i)) && !Character.isDigit(name.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
