package com.inflearn.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Delivery extends BaseEntity {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "delivery_id")
	private Long id;

//	@Getter
//	@Setter
//	private String city;
//	
//	@Getter
//	@Setter
//	private String street;
//
//	@Getter
//	@Setter
//	private String zipcode;

	@Getter
	@Setter
	@Embedded
	private Address address;
	
	@Getter
	@Setter
	private DeliveryStatus status;
	
	@Getter
	@Setter
	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
	private Order order;
}
