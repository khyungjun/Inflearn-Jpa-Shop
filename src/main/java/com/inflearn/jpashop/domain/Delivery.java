package com.inflearn.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Delivery {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "delivery_id")
	private Long id;

	@Getter
	@Setter
	private String street;

	@Getter
	@Setter
	private String zipcode;

	@Getter
	@Setter
	private DeliveryStatus status;
	
	@Getter
	@Setter
	@OneToOne(mappedBy = "delivery")
	private Order order;
}
