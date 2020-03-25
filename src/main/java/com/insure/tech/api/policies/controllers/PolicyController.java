package com.insure.tech.api.policies.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.insure.tech.api.policies.Model.PolicyDto;
import com.insure.tech.api.policies.common.HelperUtility;
import com.insure.tech.api.policies.entity.Policy;
import com.insure.tech.api.policies.repository.PolicyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/policy")
@RestController
public class PolicyController {

	private final PolicyRepository policyRepository;

	@GetMapping("/{id}")
	ResponseEntity<PolicyDto> getPolicy(@PathVariable UUID id) {
		return new ResponseEntity<>(policyToPolicyDto(policyRepository.findById(id).get()), HttpStatus.OK);
	}

	@GetMapping("/")
	public List<Policy> getPolicies() {
		return (List<Policy>) policyRepository.findAll();
	}

	@PostMapping("/")
	public ResponseEntity<PolicyDto> createPolicy(@RequestBody @Validated PolicyDto policyDto) {
		PolicyDto newPolicyDto = policyToPolicyDto(policyRepository.save(policyDtoToPolicy(policyDto)));
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Location", "/api/v1/customer/" + newPolicyDto.getId().toString());

		return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{id}")
	public void updatePolicy(@PathVariable UUID id, PolicyDto policyDto) {
		Policy policyEntity = policyRepository.findById(id).get(); // TODO: need to create service and mapper
		policyEntity.setPolicyNumber(policyDto.getPolicyNumber()); // TODO: need to handle not found exception

		policyEntity.setLastModifiedDate(new Timestamp(System.currentTimeMillis())); // TODO: need to handle with pre
																						// @anotation
		policyEntity.setPayementOption(policyDto.getPayementOption());
		policyRepository.save(policyEntity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePolicy(@PathVariable UUID id) {

		Policy policyEntity = policyRepository.findById(id).get();
		policyRepository.delete(policyEntity);

	}

	private Policy policyDtoToPolicy(PolicyDto policyDto) {
		return Policy.builder().id(UUID.randomUUID()).policyNumber(policyDto.getPolicyNumber())
				.policyExpiryDate(new Timestamp(System.currentTimeMillis())).active(Boolean.TRUE)
				.createdDate(new Timestamp(System.currentTimeMillis()))
				.lastModifiedDate(new Timestamp(System.currentTimeMillis()))
				.payementOption(policyDto.getPayementOption()).totalAmount(policyDto.getTotalAmount())
				.policyEffectiveDate(new Timestamp(System.currentTimeMillis())).build();

	}

	private PolicyDto policyToPolicyDto(Policy policy) {
		PolicyDto policyDto = new PolicyDto();
		policyDto.setId(policy.getId());
		policyDto.setPolicyNumber(policy.getPolicyNumber());
		policyDto.setPayementOption(policy.getPayementOption());
		policyDto.setTotalAmount(policy.getTotalAmount());
		policyDto.setPolicyEffectiveDate(HelperUtility.asOffsetDateTime(policy.getPolicyEffectiveDate()));
		policyDto.setPolicyExpiryDate(HelperUtility.asOffsetDateTime(policy.getPolicyExpiryDate()));
		policyDto.setCreatedDate(HelperUtility.asOffsetDateTime(policy.getCreatedDate()));
		policyDto.setLastModifiedDate(HelperUtility.asOffsetDateTime(policy.getLastModifiedDate()));

		return policyDto;
	}

}
