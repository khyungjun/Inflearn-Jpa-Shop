package com.inflearn.jpashop.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Album extends Item {
	
	@Getter
	@Setter
	private String artist;

	@Getter
	@Setter
	private String etc;
}
