package com.inflearn.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

//	@Getter
//	@Setter
//	@Column(name = "member_id")
//	private Long memberId; // 데이터 중심 설계의 문제점. 객체지향적이지 않음. 이 방식은 객체 설계를 테이블 설계에 맞춘 방식. 테이블의 외래키를 객체에 그대로 가져옴. 객체 그래프 탐색이 불가능. 참조가 없으므로 UML도 불가능.
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member; // 참조를 사용하도록 변경.
	
	
	@Getter
	@Setter
	private LocalDateTime orderDate; // 보통 DB에서 관례로 ORDER_DATE 또는 order_date를 많이 사용하기 때문에 springboot에서는 하이버네트로 걸어서 jpa를 올리면 기본 설정을 order_date로 가져간다. 자바의 camel case를 읽어서 자동으로 order_date로 가져가는 설정을 한다. 

	@Getter
	@Setter
	@Enumerated(EnumType.STRING) // ORDINAL , STRING 중 STRING을 사용하도록 한다. ORDINAL는 나중에 수정 할 때 큰 에러가 날 수 있다. 
	private OrderStatus status;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<>(); // Order 입장에서는 OrderItems가 의미가 있다. 비즈니스적으로 충분히 가치가 있으므로 양방향으로 만든다. 주문서를 뽑았는데 그 주문서와 연관된 아이템 목록을 찾거나 할 경우가 충분히 있을 수 있다.

	// 연관관계 편의 메소드 (양방향 연관관계 이므로 연관관계 편의 메소드를 굳이 만드는 것이다.)
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
}
