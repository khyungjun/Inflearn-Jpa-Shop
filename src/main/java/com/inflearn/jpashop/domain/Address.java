package com.inflearn.jpashop.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Embeddable // 값 타입
public class Address {

	@Getter
	@Setter(value = AccessLevel.PRIVATE) // setter는 막거나 private으로 만들도록 한다.
	@Column(length = 10)
	private String city;
	
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	@Column(length = 20)
	private String street;

	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	@Column(length = 5)
	private String zipcode;

	
	// 값 타입에 컬럼에 공통으로 관리되는 룰이나 의미있는 메소드(fullAddress())나 Validation 메소드(isValid()) 등을 만들어서 공통으로 사용할 수 있는 장점이 있다. 이것이 값 타입을 의미있게 사용하는 것이다. 객체 지향적으로 더 많이 사용하게 되는 것이다.
	public String fullAddress() {
		return getCity() + " " + getStreet() + " " + getZipcode();
	}
	
	public boolean isValid() { 
		if(getCity() == null || getStreet() == null || getZipcode() == null) 
			return false;
		
		return true;
	}
	
//	equals와 hashCode 메소드는 직접 짜지말고 Source -> Generate hashCode() and equals()... 를 통해 생성하도록 한다.
//	intelliJ는 Use getters during code generation 옵션을 사용해서 생성하면 city, street, zipcode 필드를 직접 사용하지 않고 getter를 호출한다.(getCity(), getStreet(), getZipcode()를 사용하도록 할 수 있다.)
//	Spring에는 그런 옵션이 없는 것 같으니 해당 필드에 직접 사용하지 않고 getter를 호출해서(getCity(), getStreet(), getZipcode()를 이용해서) 접근하도록 수정한다.
//	필드에 직접 접근하게 되면 프록시일 때는 계산이 안된다. getter를 호출해야 getter가 프록시 객체가 진짜 객체한테 가거나 하도록 할 수 있다.(프록시 객체가 진짜 객체를 찾도록) 
//	그래서 JPA에서는 프록시 때문에 이것을 고려해서 항상 메서드를 통해서 값을 호출하도록 equals()와 hashCode()를 구현하도록 하는 것이 좋다. 굳이 equals()와 hashCode()가 아니더라도 대부분의 코드를 그렇게 짜야 안전하다. 
//	@Override 
//	public int hashCode() {
//		return Objects.hash(city, street, zipcode);
//	}
	@Override
	public int hashCode() {
		return Objects.hash(getCity(), getStreet(), getZipcode());
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Address other = (Address) obj;
//		return Objects.equals(city, other.city) && Objects.equals(street, other.street)
//				&& Objects.equals(zipcode, other.zipcode);
//	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(getCity(), other.getCity()) && Objects.equals(getStreet(), other.getStreet())
				&& Objects.equals(getZipcode(), other.getZipcode());
	}
	
}
