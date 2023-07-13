package com.example.resourcemanagerapp.service;


import com.example.resourcemanagerapp.additionalTypes.ResourceType;
import com.example.resourcemanagerapp.model.Resource;
import com.example.resourcemanagerapp.model.User;
import com.example.resourcemanagerapp.repository.ResourceRepository;
import com.example.resourcemanagerapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    @Override
    public void deleteResource(Integer id) {
        resourceRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void addResource(String name, Integer userId, ResourceType type, String metadata) {
        Optional<User> User = userRepository.findById(userId);
        if(User.isEmpty()){
            return;
        }
        LocalDate currentDate = LocalDate.now();
        Resource resource = Resource.builder().name(name).userId(User.get()).type(type).
                metadata(metadata).creationTime(currentDate).modificationTime(currentDate).build();
        resourceRepository.save(resource);
    }

    @Transactional
    @Override
    public void changeResourceName(Integer id, String newName) {
        Optional<Resource> resource = resourceRepository.findById(id);
        if(resource.isEmpty()){
            return;
        }
        Resource resource1 = resource.get();
        resource1.setName(newName);
        resource1.setModificationTime(LocalDate.now());
        resourceRepository.save(resource1);
    }

    @Transactional
    @Override
    public void changeResourceMetadata(Integer id, ResourceType type, String metadata) {
        Optional<Resource> resource = resourceRepository.findById(id);
        if(resource.isEmpty()){
            return;
        }
        Resource resource1 = resource.get();
        resource1.setType(type);
        resource1.setMetadata(metadata);
        resource1.setModificationTime(LocalDate.now());
        resourceRepository.save(resource1);
    }
}
