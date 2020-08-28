package com.ulht.pw.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String productName;
	private String productCode;
	private String description;
	private LocalDate expireDate;
	private String brand;
	private Long price;


	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductPrecautionsEntity> productPrecautions = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_id", nullable = false)
	private SaleEntity sale;



}
