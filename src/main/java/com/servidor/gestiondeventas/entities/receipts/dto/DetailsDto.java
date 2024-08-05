package com.servidor.gestiondeventas.entities.receipts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.company.dto.CompanyDTO;
import com.servidor.gestiondeventas.entities.persons.dto.CustomerDTO;
import com.servidor.gestiondeventas.entities.persons.dto.SalesPersonDTO;
import com.servidor.gestiondeventas.entities.receipts.Details;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DetailsDto {
    private Long id;
    private String cae;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date caeFchVto;

    private SalesPersonDTO salesPerson;
    private CustomerDTO customer;
    private CompanyDTO company;
    private String paymentType;
    private String fiscalStatus;
    private List<DetailProductsDto> detailProductList;
    private String numberInvoice;
    private Double total;
    private Double totalCost;
    private Double discount;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;

    static public DetailsDto fromEntity(Details details){
        DetailsDto detailsDto = new DetailsDto();

        detailsDto.setId(details.getId());
        detailsDto.setDetailProductList(details.getDetailProductList().stream()
                .map(DetailProductsDto:: fromEntity)
                .collect(Collectors.toList()));

        detailsDto.setTotalCost(details.getCostTotal());
        detailsDto.setTotal(details.getTotal());
        detailsDto.setDiscount(details.getDiscount());
        detailsDto.setSalesPerson(SalesPersonDTO.fromFamily(details.getSalesPerson()));
        detailsDto.setCustomer(CustomerDTO.fromEntity(details.getCustomer()));
        detailsDto.setPaymentType(details.getPaymentType());
        detailsDto.setFiscalStatus(details.getFiscalStatus());
        detailsDto.setCompany(CompanyDTO.fromEntity(details.getCompany()));
        detailsDto.setCae(details.getCae());
        detailsDto.setCaeFchVto(details.getCaeFchVto());
        detailsDto.setNumberInvoice(details.getNumberInvoice());


        // Convertir ZonedDateTime a Date

        ZonedDateTime ahoraEnArgentina = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));

        Date fechaActual = Date.from(ahoraEnArgentina.toInstant());

        detailsDto.setDate(fechaActual);
        return detailsDto;
    }
}
