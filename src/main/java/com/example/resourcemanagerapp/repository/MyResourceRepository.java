package com.example.resourcemanagerapp.repository;

import com.example.resourcemanagerapp.model.MyResource;
import com.example.resourcemanagerapp.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyResourceRepository extends JpaRepository<MyResource, Integer> {
    List<MyResource> findAllByUserId(MyUser user);
}
