package com.inflearn.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class OrderItem {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_item_id")
	private Long id;

//	@Getter
//	@Setter
//	@Column(name = "order_id")
//	private Long orderId;
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

//	@Getter
//	@Setter
//	@Column(name = "item_id")
//	private Long itemId;
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;


	@Getter
	@Setter
	private int orderPrice;

	@Getter
	@Setter
	private int count;
}
