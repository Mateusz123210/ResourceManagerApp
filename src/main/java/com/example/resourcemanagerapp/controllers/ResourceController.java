package com.example.resourcemanagerapp.controllers;


import com.example.resourcemanagerapp.dtos.AddResourceDTO;
import com.example.resourcemanagerapp.dtos.UpdateResourceMetadataDTO;
import com.example.resourcemanagerapp.dtos.UpdateResourceNameDTO;
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
    public ResponseEntity addResource(@RequestBody AddResourceDTO addResourceDTO){
        return resourceService.addResource(addResourceDTO);
    }

    @DeleteMapping
    public ResponseEntity deleteResource(@RequestParam Integer id){
        return resourceService.deleteResource(id);
    }

    @PutMapping(value = "/update-name")
    public ResponseEntity updateResourceName(@RequestBody UpdateResourceNameDTO updateResourceNameDTO){
        return resourceService.changeResourceName(updateResourceNameDTO);
    }

    @PutMapping(value = "/update-metadata")
    public ResponseEntity updateResourceMetadata(@RequestBody UpdateResourceMetadataDTO updateResourceMetadataDTO){
         return resourceService.changeResourceMetadata(updateResourceMetadataDTO);
    }
}
