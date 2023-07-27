package com.example.resourcemanagerapp.repositories;

import com.example.resourcemanagerapp.models.ResourceEntity;
import com.example.resourcemanagerapp.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Integer> {
    List<ResourceEntity> findAllByUserId(UserEntity userEntity);
}
