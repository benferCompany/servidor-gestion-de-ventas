package com.servidor.gestiondeventas.entities.receipts.dto;

import com.servidor.gestiondeventas.entities.persons.SalesPerson;
import com.servidor.gestiondeventas.entities.persons.dto.CustomerDTO;
import com.servidor.gestiondeventas.entities.persons.dto.SalesPersonDTO;
import com.servidor.gestiondeventas.entities.receipts.DetailProduct;
import com.servidor.gestiondeventas.entities.receipts.Details;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DetailsDto {
    private Long id;
    private SalesPersonDTO salesPerson;
    private CustomerDTO customer;
    private String paymentType;
    private String fiscalStatus;
    private List<DetailProductsDto> detailProductList;
    private double total;
    static public DetailsDto fromEntity(Details details){
        DetailsDto detailsDto = new DetailsDto();

        detailsDto.setId(details.getId());
        detailsDto.setDetailProductList(details.getDetailProductList().stream()
                .map(DetailProductsDto:: fromEntity)
                .collect(Collectors.toList()));
        double totalSum = detailsDto.getDetailProductList().stream()
                .mapToDouble(DetailProductsDto::getTotal) // Convierte cada DetailProduct a su valor total
                .sum(); // Suma todos los valores totales
        detailsDto.setTotal(totalSum);
        detailsDto.setSalesPerson(SalesPersonDTO.fromFamily(details.getSalesPerson()));
        detailsDto.setCustomer(CustomerDTO.fromEntity(details.getCustomer()));
        detailsDto.setPaymentType(details.getPaymentType());
        detailsDto.setFiscalStatus(details.getFiscalStatus());
        return detailsDto;
    }
}
