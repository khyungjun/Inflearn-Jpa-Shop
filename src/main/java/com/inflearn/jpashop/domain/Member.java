package com.inflearn.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Member extends BaseEntity {

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
	
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>(); // 양방향 예제니까 추가했지만 사실 member에서 orders를 추가하는건 좋지 않다. 객체 지향이라고 무조건 양방향으로 하는 것이 아니라 연관 관계를 잘 끊어내는 것이 설계에서 중요하다. 주문이 필요하면 order에서 시작해서 정보를 찾는 것이 좋다.
}
