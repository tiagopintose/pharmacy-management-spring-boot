package com.ulht.pw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ulht.pw.domain.ClientEntity;
import com.ulht.pw.domain.SaleEntity;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    Optional<SaleEntity> findById(Long saleId);
}
