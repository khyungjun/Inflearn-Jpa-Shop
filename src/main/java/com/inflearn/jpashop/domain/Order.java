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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import static javax.persistence.FetchType.*;
import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "ORDERS") // order가 예약어(ex. order by)로 걸려있는 DB가 있기 때문에 보통 ORDERS로 많이 사용한다. 
public class Order extends BaseEntity {

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
	@ManyToOne(fetch = LAZY) // 다대일은 무조건 다 쪽이 연관관계의 주인이 되어야 한다. @ManyToOne, @OneToOne은 모두 지연 로딩으로 변경시켜준다.
	@JoinColumn(name = "member_id")
	private Member member; // 참조를 사용하도록 변경.다.
	
	
	// 일대일 관계는 외래 키를 양쪽 어디나 둘 수 있음. 
	// Orders에 두면 성능(바로 확인 가능, 나중에 프록시 등등) + 객체 입장에서 편리함
	// Delivery에 두면 1 -> N으로 확장이 편리함(DB 컬럼 변경 없이 N으로 변경 가능)
	// 다대다 관계 -> 테이블은 중간테이블을 만들고 일대다 다대일 관계로 풀어야 한다.
	@Getter
	@Setter
	@OneToOne(fetch = LAZY, cascade = ALL) // Order -> Delivery를 영속성 전이 ALL 설정 : order를 생성해서 delivery를 넣을 때 자동으로 order를 저장하면 delivery도 저장된다.
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;

	@OneToMany(mappedBy = "order", cascade = ALL) // Order -> OrderItem을 영속성 전이 ALL 설정
	private List<OrderItem> orderItems = new ArrayList<>(); // Order 입장에서는 OrderItems가 의미가 있다. 비즈니스적으로 충분히 가치가 있으므로 양방향으로 만든다. 주문서를 뽑았는데 그 주문서와 연관된 아이템 목록을 찾거나 할 경우가 충분히 있을 수 있다.

	@Getter
	@Setter
	private LocalDateTime orderDate; // 보통 DB에서 관례로 ORDER_DATE 또는 order_date를 많이 사용하기 때문에 springboot에서는 하이버네트로 걸어서 jpa를 올리면 기본 설정을 order_date로 가져간다. 자바의 camel case를 읽어서 자동으로 order_date로 가져가는 설정을 한다. 

	@Getter
	@Setter
	@Enumerated(EnumType.STRING) // ORDINAL , STRING 중 STRING을 사용하도록 한다. ORDINAL는 나중에 수정 할 때 큰 에러가 날 수 있다. 
	private OrderStatus status;
	
	// 연관관계 편의 메소드 (양방향 연관관계 이므로 연관관계 편의 메소드를 굳이 만드는 것이다.)
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
}
