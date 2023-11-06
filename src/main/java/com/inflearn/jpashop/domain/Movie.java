package com.inflearn.jpashop.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Movie extends Item {
	
	@Getter
	@Setter
	private String director;

	@Getter
	@Setter
	private String actor;
}
