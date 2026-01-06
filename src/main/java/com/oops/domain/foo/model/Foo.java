package com.oops.domain.foo.model;

import com.oops.outbound.mysql.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "foo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Foo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = 0L;

	@Column(name = "description")
	private String description;

}
