package com.inflearn.jpashop.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDERS") // order가 예약어(ex. order by)로 걸려있는 DB가 있기 때문에 보통 ORDERS로 많이 사용한다. 
public class Order {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Long id;

	@Getter
	@Setter
	@Column(name = "member_id")
	private Long memberId; // 데이터 중심 설계의 문제점. 객체지향적이지 않음. 이 방식은 객체 설계를 테이블 설계에 맞춘 방식. 테이블의 외래키를 객체에 그대로 가져옴. 객체 그래프 탐색이 불가능. 참조가 없으므로 UML도 불가능.

	@Getter
	@Setter
	private LocalDateTime orderDate; // 보통 DB에서 관례로 ORDER_DATE 또는 order_date를 많이 사용하기 때문에 springboot에서는 하이버네트로 걸어서 jpa를 올리면 기본 설정을 order_date로 가져간다. 자바의 camel case를 읽어서 자동으로 order_date로 가져가는 설정을 한다. 

	@Getter
	@Setter
	@Enumerated(EnumType.STRING) // ORDINAL , STRING 중 STRING을 사용하도록 한다. ORDINAL는 나중에 수정 할 때 큰 에러가 날 수 있다. 
	private OrderStatus status;
}
