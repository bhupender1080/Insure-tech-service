package com.insure.tech.api.policies.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.insure.tech.api.policies.entity.Policy;



public interface PolicyRepository extends CrudRepository<Policy, UUID>{

}
