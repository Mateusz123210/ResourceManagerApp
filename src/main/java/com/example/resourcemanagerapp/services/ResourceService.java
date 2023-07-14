package com.example.resourcemanagerapp.services;


import com.example.resourcemanagerapp.additionalTypes.ResourceType;
import com.example.resourcemanagerapp.dtos.AddResourceDTO;
import com.example.resourcemanagerapp.dtos.UpdateResourceMetadataDTO;
import com.example.resourcemanagerapp.dtos.UpdateResourceNameDTO;
import com.example.resourcemanagerapp.exceptions.ApplicationException;
import com.example.resourcemanagerapp.models.ResourceEntity;
import com.example.resourcemanagerapp.models.UserEntity;
import com.example.resourcemanagerapp.repositories.ResourceRepository;
import com.example.resourcemanagerapp.repositories.UserRepository;
import com.example.resourcemanagerapp.validators.ResourceApiParamsValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity addResource(AddResourceDTO addResourceDTO) {
        String name = addResourceDTO.getName();
        Integer userId = addResourceDTO.getUserId();
        ResourceType type = addResourceDTO.getType();
        String metadata = addResourceDTO.getMetadata();
        ResourceApiParamsValidator.validateAddResourceParameters(name, userId, type, metadata);
        UserEntity user = userRepository.findById(addResourceDTO.getUserId()).
                orElseThrow(() -> new ApplicationException("Resource was not added. User with this id does not exist!"));
        LocalDateTime currentDateTime = LocalDateTime.now();
        ResourceEntity resourceEntity = ResourceEntity.builder()
                .name(addResourceDTO.getName())
                .userId(user)
                .type(addResourceDTO.getType())
                .metadata(addResourceDTO.getMetadata())
                .creationTime(currentDateTime)
                .modificationTime(currentDateTime)
                .build();
        resourceRepository.save(resourceEntity);
        return ResponseEntity.ok("Resource was added!");
    }

    @Transactional
    public ResponseEntity deleteResource(Integer id) {
        ResourceApiParamsValidator.validateResourceId(id);
        ResourceEntity resource = resourceRepository.findById(id).
                orElseThrow(() -> new ApplicationException
                        ("Resource was not deleted. Resource with this id does not exist!"));
        resourceRepository.deleteById(id);
        return ResponseEntity.ok("Resource was deleted!");
    }

    @Transactional
    public ResponseEntity changeResourceName(UpdateResourceNameDTO updateResourceNameDTO) {
        Integer id = updateResourceNameDTO.getId();
        String newName = updateResourceNameDTO.getNewName();
        ResourceApiParamsValidator.validateUpdateResourceNameParameters(id, newName);
        ResourceEntity resource = resourceRepository.findById(id).orElseThrow(
                () -> new ApplicationException(
                        "Resource name was not changed. Resource with this id does not exist!"));
        resource.setName(newName);
        resource.setModificationTime(LocalDateTime.now());
        resourceRepository.save(resource);
        return ResponseEntity.ok("Resource name was changed!");
    }

    @Transactional
    public ResponseEntity changeResourceMetadata(UpdateResourceMetadataDTO updateResourceMetadataDTO) {
        Integer id  = updateResourceMetadataDTO.getId();
        ResourceType type = updateResourceMetadataDTO.getMetadataType();
        String metadata = updateResourceMetadataDTO.getMetadata();
        ResourceEntity resource = resourceRepository.findById(id).orElseThrow(
            () -> new ApplicationException(
                    "Resource name was not changed. Resource with this id does not exist!"));
        resource.setType(type);
        resource.setMetadata(metadata);
        resource.setModificationTime(LocalDateTime.now());
        resourceRepository.save(resource);
        return ResponseEntity.ok("Resource metadata was changed!");
    }
}
