package com.example.resourcemanagerapp.services;


import com.example.resourcemanagerapp.additionalTypes.ResourceType;
import com.example.resourcemanagerapp.dtos.ResourceDTO;
import com.example.resourcemanagerapp.dtos.ResourceMetadataDTO;
import com.example.resourcemanagerapp.dtos.ResourceNameDTO;
import com.example.resourcemanagerapp.exceptions.*;
import com.example.resourcemanagerapp.models.ResourceEntity;
import com.example.resourcemanagerapp.models.UserEntity;
import com.example.resourcemanagerapp.repositories.ResourceRepository;
import com.example.resourcemanagerapp.repositories.UserRepository;
import com.example.resourcemanagerapp.validators.ResourceApiParamsValidator;
import com.example.resourcemanagerapp.validators.UserApiParamsValidator;
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
    public ResponseEntity addResource(ResourceDTO resourceDTO) {
        String name = resourceDTO.getName();
        Integer userId = resourceDTO.getUserId();
        ResourceType type = resourceDTO.getType();
        String metadata = resourceDTO.getMetadata();

        ResourceApiParamsValidator.validateAddResourceParameters(name, userId, type, metadata);

        UserEntity user = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException
                        ("Resource was not added. User with this id does not exist!"));
        LocalDateTime currentDateTime = LocalDateTime.now();
        ResourceEntity resourceEntity = ResourceEntity.builder()
                .name(name)
                .userId(user)
                .type(type)
                .metadata(metadata)
                .creationTime(currentDateTime)
                .modificationTime(currentDateTime)
                .build();

        resourceRepository.save(resourceEntity);

        return ResponseEntity.ok("Resource was added!");
    }

    @Transactional
    public ResponseEntity deleteResource(Integer resourceId, Integer authorizedUserId) {
        UserApiParamsValidator.validateUserId(authorizedUserId);

        UserEntity authorizedUser = userRepository.findById(authorizedUserId)
                .orElseThrow(() -> new AuthorizedUserNotFoundException
                        ("Resource was not deleted. Authorized user does not exist!"));

        ResourceApiParamsValidator.validateResourceId(resourceId);

        ResourceEntity resource = resourceRepository.findById(resourceId)
            .orElseThrow(() -> new ResourceNotFoundException
                    ("Resource was not deleted. Resource with this id does not exist!"));

        if(resource.getUserId() != authorizedUser){
            throw new UserNotPermittedException
                    ("Resource was not deleted. Resource does not belong to this user!");
        }
        resourceRepository.deleteById(resourceId);

        return ResponseEntity.ok("Resource was deleted!");
    }

    @Transactional
    public ResponseEntity changeResourceName(ResourceNameDTO resourceNameDTO) {
        Integer id = resourceNameDTO.getResourceId();
        String newName = resourceNameDTO.getNewName();

        ResourceApiParamsValidator.validateUpdateResourceNameParameters(id, newName);

        ResourceEntity resource = resourceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Resource name was not changed. Resource with this id does not exist!"));

        resource.setName(newName);
        resource.setModificationTime(LocalDateTime.now());
        resourceRepository.save(resource);

        return ResponseEntity.ok("Resource name was changed!");
    }

    @Transactional
    public ResponseEntity changeResourceMetadata(ResourceMetadataDTO resourceMetadataDTO) {
        Integer id  = resourceMetadataDTO.getResourceId();
        ResourceType type = resourceMetadataDTO.getMetadataType();
        String metadata = resourceMetadataDTO.getMetadata();

        ResourceApiParamsValidator.validateUpdateResourceMetadataParameters(id, type, metadata);

        ResourceEntity resource = resourceRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(
                    "Resource name was not changed. Resource with this id does not exist!"));

        resource.setType(type);
        resource.setMetadata(metadata);
        resource.setModificationTime(LocalDateTime.now());
        resourceRepository.save(resource);

        return ResponseEntity.ok("Resource metadata was changed!");
    }
}
