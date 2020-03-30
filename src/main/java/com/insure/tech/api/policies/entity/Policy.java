package com.insure.tech.api.policies.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "policies")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Policy implements Serializable {

	private static final long serialVersionUID = -2731425678149216053L;

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
	private UUID id;
	
	@Column(length = 36, columnDefinition = "varchar(36)", nullable = false)
	@Type(type="org.hibernate.type.UUIDCharType")
	private UUID customerId;

	@Column(nullable = false, unique = true)
	private String policyNumber;

	@Column(nullable = false)
	private Timestamp policyEffectiveDate;

	@Column(nullable = false)
	private Timestamp policyExpiryDate;

	@Column(nullable = false, length = 100)
	private String payementOption;

	@Column(nullable = false)
	private BigDecimal totalAmount;
	
	@Column(nullable = false)
	private Boolean active;
	
	@Column(length = 100)
	private String additionalInfo;
	
	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdDate;
	
	@UpdateTimestamp
	private Timestamp lastModifiedDate;

}
