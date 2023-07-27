package com.example.resourcemanagerapp.controllers;


import com.example.resourcemanagerapp.dtos.ResourceDTO;
import com.example.resourcemanagerapp.dtos.ResourceMetadataDTO;
import com.example.resourcemanagerapp.dtos.ResourceNameDTO;
import com.example.resourcemanagerapp.services.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/resource")
@RequiredArgsConstructor
@RestController
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity addResource(@RequestBody ResourceDTO resourceDTO){
        return resourceService.addResource(resourceDTO);
    }

    @DeleteMapping
    public ResponseEntity deleteResource(@RequestParam Integer resourceId, @RequestParam Integer authorizedUserId){
        return resourceService.deleteResource(resourceId, authorizedUserId);
    }

    @PutMapping(value = "/update-name")
    public ResponseEntity updateResourceName(@RequestBody ResourceNameDTO resourceNameDTO){
        return resourceService.changeResourceName(resourceNameDTO);
    }

    @PutMapping(value = "/update-metadata")
    public ResponseEntity updateResourceMetadata(@RequestBody ResourceMetadataDTO resourceMetadataDTO){
         return resourceService.changeResourceMetadata(resourceMetadataDTO);
    }
}
