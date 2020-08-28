package com.ulht.pw.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ulht.pw.controller.rest.errors.ResourceNotFoundException;
import com.ulht.pw.domain.ClientEntity;
import com.ulht.pw.domain.SaleEntity;
import com.ulht.pw.dto.sale.SaleDTO;
import com.ulht.pw.repository.ClientRepository;
import com.ulht.pw.repository.SaleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ma.glasnost.orika.MapperFacade;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaleService {

    private static final String DOMAIN_NAME = "SaleEntity";

    private final SaleRepository saleRepository;

    private final MapperFacade mapper;

    public SaleDTO searchSaleById(Long saleID) {
        SaleEntity sale = saleRepository.findById(saleID)
                .orElseThrow(() -> new ResourceNotFoundException(DOMAIN_NAME, "id", saleID));
        return mapper.map(sale, SaleDTO.class);
    }

    public List<SaleDTO> findAllSales() {
        return mapper.mapAsList(saleRepository.findAll(), SaleDTO.class);
    }

    @Transactional
    public SaleDTO createSale(SaleDTO sale) {
        SaleEntity saleEntity = handSaleSave(sale);
        return mapper.map(saleRepository.save(saleEntity), SaleDTO.class);
    }

    @Transactional
    public SaleDTO updateSale(SaleDTO sale) {
        SaleEntity saleEntity = handSaleSave(sale);
        return mapper.map(saleRepository.save(saleEntity), SaleDTO.class);
    }

    private SaleEntity handSaleSave(SaleDTO sale) {
        SaleEntity saleEntity = mapper.map(sale, SaleEntity.class);
        saleEntity.getProducts().forEach(product -> product.setSale(saleEntity));
        return saleEntity;
    }

    @Transactional
    public void deleteSaleById(Long saleId) {
        saleRepository.findById(saleId).ifPresent(sale -> {
            saleRepository.delete(sale);
            log.debug("Deleted Sale: {}", sale);
        });
    }
}
