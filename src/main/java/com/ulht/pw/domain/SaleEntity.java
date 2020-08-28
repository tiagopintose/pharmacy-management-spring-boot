package com.ulht.pw.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SaleEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String description;
    private Long total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "sale_product", joinColumns = {
            @JoinColumn(name = "sales_id", referencedColumnName = "id") },
            inverseJoinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id") })
    private Set<ProductEntity> products = new HashSet<>();

}
