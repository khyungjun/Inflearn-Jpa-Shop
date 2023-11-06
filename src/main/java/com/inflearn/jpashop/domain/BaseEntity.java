package com.inflearn.jpashop.domain;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class BaseEntity {

	@Getter
	@Setter
	private String createdBy;

	@Getter
	@Setter
	private LocalDateTime createdDate;

	@Getter
	@Setter
	private String lastModifiedBy;

	@Getter
	@Setter
	private LocalDateTime lastModifiedDate;
}
