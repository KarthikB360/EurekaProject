package com.eureka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eureka.model.Delivery;

@Repository
public interface GenericRepository extends JpaRepository<Delivery, Integer> {

}
