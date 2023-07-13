package com.example.resourcemanagerapp.controllers;


import com.example.resourcemanagerapp.additionalTypes.EnumChecker;
import com.example.resourcemanagerapp.additionalTypes.ResourceType;
import com.example.resourcemanagerapp.mappers.AddResourceDTO;
import com.example.resourcemanagerapp.mappers.EditResourceMetadataDTO;
import com.example.resourcemanagerapp.services.ResourceService;
import com.example.resourcemanagerapp.validators.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping(value = "/resource")
    public ResponseEntity addResource(@RequestBody AddResourceDTO addResourceDTO){
        String name = addResourceDTO.getName();
        Integer userId = addResourceDTO.getUserId();
        String type = addResourceDTO.getType();
        String metadata = addResourceDTO.getMetadata();
        if(name.length() == 0 || userId <= 0 || type.length() == 0 || metadata.length() == 0 ||
                !Validator.checkIfContainsOnlyLetters(name)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        }
        ResourceType resourceType = EnumChecker.containsResourceType(type);
        if( resourceType == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        }
        try{
            new JSONObject(metadata);
        }catch(JSONException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        }
        resourceService.addResource(name, userId, resourceType, metadata);
        return ResponseEntity.ok("");
    }

    @DeleteMapping(value = "/resource")
    public ResponseEntity deleteResource(@RequestParam Integer id){
        if(id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        }
        resourceService.deleteResource(id);
        return ResponseEntity.ok("");
    }

    @PutMapping(value = "/resource/edit-name")
    public ResponseEntity editResourceName(@RequestParam Integer id, @RequestParam String newName){
        if(id <= 0 || !Validator.checkIfContainsOnlyLetters(newName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        }
        resourceService.changeResourceName(id, newName);
        return ResponseEntity.ok("");
    }

    @PutMapping(value = "/resource/edit-metadata")
    public ResponseEntity editResourceMetadata(@RequestBody EditResourceMetadataDTO editResourceMetadataDTO){
        Integer id  = editResourceMetadataDTO.getId();
        String metadataType = editResourceMetadataDTO.getMetadataType();
        String metadata = editResourceMetadataDTO.getMetadata();
        if(id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        }
        ResourceType resourceType = EnumChecker.containsResourceType(metadataType);
        if( resourceType == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        }
        try{
            new JSONObject(metadata);
        }catch(JSONException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid params");
        }
        resourceService.changeResourceMetadata(id, resourceType, metadata);
        return ResponseEntity.ok("");
    }
}