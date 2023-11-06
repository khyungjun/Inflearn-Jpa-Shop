package com.inflearn.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Category extends BaseEntity {
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private Long id;

	@Getter
	@Setter
	private String name;

	@ManyToOne(fetch = FetchType.LAZY) // 모든 연관관계를 지연 로딩으로 하도록 한다. @ManyToOne, @OneToOne은 기본이 즉시 로딩이므로 지연 로딩으로 변경한다.
	@JoinColumn(name = "parent_id")
	private Category parent; // 상위 카테고리. 셀프로 매핑한 것이다.
	
	@OneToMany(mappedBy = "parent") // @OneToMany는 기본 타입이 Lazy(지연 로딩)이다.
	private List<Category> child = new ArrayList<>(); // 카테고리가 쭉 내려가는 것을 셀프로 만든 것이다.
	
	// 테이블의 N:M 관계는 중간 테이블을 이용해서 1:N, N:1로 풀도록 한다. 실전에서는 중간테이블이 단순하지 않다.
	// ManyToMany는 필드 추가 X, 엔티티 테이블 불일치 등의 제약이 있다. 따라서 실전에서는 @ManyToMay를 사용하지 않는다.
	@ManyToMany // 다대다 예시기 때문에 만든 것. 실무에서 절대 사용하지 않도록 한다. 다대다 관계는 중간테이블을 만들고 일대다 다대일 관계로 풀어야 한다.
	@JoinTable(name = "CATEGORY_ITEM",
			joinColumns = @JoinColumn(name = "category_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id")
	)
	private List<Item> items = new ArrayList<>();
}
