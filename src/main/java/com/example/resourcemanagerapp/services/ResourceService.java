package com.example.resourcemanagerapp.services;


import com.example.resourcemanagerapp.additionalTypes.ResourceType;
import com.example.resourcemanagerapp.models.ResourceEntity;
import com.example.resourcemanagerapp.models.UserEntity;
import com.example.resourcemanagerapp.repositories.ResourceRepository;
import com.example.resourcemanagerapp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    public void deleteResource(Integer id) {
        resourceRepository.deleteById(id);
    }

    @Transactional
    public void addResource(String name, Integer userId, ResourceType type, String metadata) {
        Optional<UserEntity> User = userRepository.findById(userId);
        if(User.isEmpty()){
            return;
        }
        LocalDate currentDate = LocalDate.now();
        ResourceEntity resourceEntity = ResourceEntity.builder().name(name).userId(User.get()).type(type).
                metadata(metadata).creationTime(currentDate).modificationTime(currentDate).build();
        resourceRepository.save(resourceEntity);
    }

    @Transactional
    public void changeResourceName(Integer id, String newName) {
        Optional<ResourceEntity> resource = resourceRepository.findById(id);
        if(resource.isEmpty()){
            return;
        }
        ResourceEntity resourceEntity1 = resource.get();
        resourceEntity1.setName(newName);
        resourceEntity1.setModificationTime(LocalDate.now());
        resourceRepository.save(resourceEntity1);
    }

    @Transactional
    public void changeResourceMetadata(Integer id, ResourceType type, String metadata) {
        Optional<ResourceEntity> resource = resourceRepository.findById(id);
        if(resource.isEmpty()){
            return;
        }
        ResourceEntity resourceEntity1 = resource.get();
        resourceEntity1.setType(type);
        resourceEntity1.setMetadata(metadata);
        resourceEntity1.setModificationTime(LocalDate.now());
        resourceRepository.save(resourceEntity1);
    }
}
