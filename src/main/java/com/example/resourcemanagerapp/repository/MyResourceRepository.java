package com.example.resourcemanagerapp.repository;

import com.example.resourcemanagerapp.model.MyResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyResourceRepository extends JpaRepository<MyResource, Integer> {

}
