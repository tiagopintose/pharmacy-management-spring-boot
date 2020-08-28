package com.ulht.pw.dto.sale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ulht.pw.dto.BaseDTO;

import com.ulht.pw.dto.client.ClientDTO;
import com.ulht.pw.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SaleDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    private String description;
    private Long total;
   // private List<ProductDTO> products = new ArrayList<>();
}
