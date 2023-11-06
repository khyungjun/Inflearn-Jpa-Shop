package com.inflearn.jpashop.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Book extends Item {
	
	@Getter
	@Setter
	private String author;

	@Getter
	@Setter
	private String isbn;
}
