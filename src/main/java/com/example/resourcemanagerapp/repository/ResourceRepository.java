package com.example.resourcemanagerapp.repository;

import com.example.resourcemanagerapp.model.Resource;
import com.example.resourcemanagerapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
    List<Resource> findAllByUserId(User user);
}
