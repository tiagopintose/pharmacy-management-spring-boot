package com.ulht.pw.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.ulht.pw.dto.sale.SaleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ulht.pw.controller.rest.errors.InvalidCreateException;
import com.ulht.pw.controller.rest.errors.InvalidUpdateException;
import com.ulht.pw.dto.client.ClientDTO;
import com.ulht.pw.dto.sale.SaleDTO;
import com.ulht.pw.service.ClientService;
import com.ulht.pw.service.SaleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {

    private static final String ENTITY_NAME = "saleEntity";

    private final SaleService saleService;

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> searchSaleById(@PathVariable(value = "id") Long saleId) {
        log.debug("REST request to get sale with Id : {}", saleId);
        return ResponseEntity.ok().body(saleService.searchSaleById(saleId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        log.debug("REST request to get all Sales");
        return ResponseEntity.ok().body(saleService.findAllSales());
    }

    @PostMapping("/save")
    public ResponseEntity<SaleDTO> createSale(@Valid @RequestBody SaleDTO sale) throws URISyntaxException {
        log.debug("REST request to save Sale : {}", sale);
        if (!sale.isNew()) {
            throw new InvalidCreateException(ENTITY_NAME);
        }

        SaleDTO result = saleService.createSale(sale);
        return ResponseEntity.created(new URI("/api/sale/" + result.getId())).body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<SaleDTO> updateSale(@Valid @RequestBody SaleDTO sale) throws URISyntaxException {
        log.debug("REST request to save SaleEntity : {}", sale);
        if (sale.isNew()) {
            throw new InvalidUpdateException(ENTITY_NAME);
        }

        SaleDTO result = saleService.updateSale(sale);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable(value = "id") Long id) {
        log.debug("REST request to delete Sale : {}", id);
        saleService.deleteSaleById(id);
        return ResponseEntity.ok().build();
    }

}
