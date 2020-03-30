package com.insure.tech.api.policies.bootstrap;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.insure.tech.api.policies.entity.Policy;
import com.insure.tech.api.policies.repository.PolicyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

	private final PolicyRepository policyRepository;

	@Override
	public void run(String... args) throws Exception {
		if (policyRepository.count() == 0) {
			loadPolicyObjects();
			
		}
System.out.println("here"+policyRepository.count());	
	}

	private void loadPolicyObjects() {
		UUID cutomerid = UUID.randomUUID();
		System.out.println("cutomerid----"+cutomerid);
		Policy p1 = Policy.builder().id(UUID.randomUUID()).policyNumber("1224")
				.policyExpiryDate(new Timestamp(System.currentTimeMillis())).active(Boolean.TRUE)
				.createdDate(new Timestamp(System.currentTimeMillis()))
				.lastModifiedDate(new Timestamp(System.currentTimeMillis())).payementOption("")
				.totalAmount(new BigDecimal("12.95")).policyEffectiveDate(new Timestamp(System.currentTimeMillis()))
				.customerId(cutomerid)
				.build();

		Policy p2 = Policy.builder().id(UUID.randomUUID()).policyNumber("1225")
				.policyExpiryDate(new Timestamp(System.currentTimeMillis())).active(Boolean.TRUE)
				.createdDate(new Timestamp(System.currentTimeMillis()))
				.lastModifiedDate(new Timestamp(System.currentTimeMillis())).payementOption("")
				.totalAmount(new BigDecimal("11.95")).policyEffectiveDate(new Timestamp(System.currentTimeMillis()))
				.customerId(cutomerid)
				.build();

		Policy p3 = Policy.builder().id(UUID.randomUUID()).policyNumber("1226")
				.policyExpiryDate(new Timestamp(System.currentTimeMillis())).active(Boolean.TRUE)
				.createdDate(new Timestamp(System.currentTimeMillis()))
				.lastModifiedDate(new Timestamp(System.currentTimeMillis())).payementOption("")
				.totalAmount(new BigDecimal("13.95")).policyEffectiveDate(new Timestamp(System.currentTimeMillis()))
				.customerId(cutomerid)
				.build();

		System.out.println("UUID ->"+policyRepository.save(p1).getId());
		System.out.println("UUID ->"+policyRepository.save(p2).getId());
		System.out.println("UUID ->"+policyRepository.save(p3).getId());
	}

		


}
