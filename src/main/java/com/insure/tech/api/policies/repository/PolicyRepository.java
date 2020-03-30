package com.insure.tech.api.policies.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insure.tech.api.policies.entity.Policy;



public interface PolicyRepository extends JpaRepository<Policy, UUID>{
	
	List<Policy> findAllByCustomerId (UUID customerId);

}
