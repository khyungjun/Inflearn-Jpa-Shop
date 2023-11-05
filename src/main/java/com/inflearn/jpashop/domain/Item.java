package com.inflearn.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Item {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private int price;

	@Getter
	@Setter
	private int stockQuanity;
	
//	하나의 item을 보고 이 item의 order_item의 연관관계를 추적하고 싶으면 양방향 관계로 만들 수도 있지만 잘 생각해보면 그렇게 할 일이 거의 없다.
//	주문 입장에서는 상품이 뭐가 들어왔는지 중요한데 상품 입장에서 보면 어떤 주문에 의해서 상품이 들어갔는지가 그렇게 중요하지 않다. 그렇게 연관관계를 찾아갈 만큼 중요하지 않다.
//	왜냐하면 대부분 주문에서 상품으로 참조가 넘어와서 오지 상품 자체를 보고 어떤 주문으로 팔렸는지가 실시간으로 그렇게 중요하지 않다. 예를 들어 마이크란 상품이 있는데 그 마이크가 어떤 주문으로 팔렸는지 실시간으로 그렇게 중요하지 않다.
//	나중에 데이터베이스 통계를 내거나 리포팅 할 때는 중요할 수 있는데 계산될 때는 주문서를 보고 아이템들을 찾지 아이템을 보고 어떤 주문서들에서 주문이 됬나를 찾을 일은 거의 없다. 
//	@OneToMany(mappedBy = "item")
//	private List<OrderItem> orderItems = new ArrayList<>();
	
	@ManyToMany(mappedBy = "items") // ManyToMany를 양방향으로 만든 것이다.
	private List<Category> categories = new ArrayList<>();
}
