package com.inflearn.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Member {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_id")
	private long id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String city;

	@Getter
	@Setter
	private String street;

	@Getter
	@Setter
	private String zipcode;
}
