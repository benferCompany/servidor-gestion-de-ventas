package com.servidor.gestiondeventas.entities.expenses.invoice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.company.dto.CompanyDTO;
import com.servidor.gestiondeventas.entities.expenses.invoice.DetailProductInvoiceSupplier;
import com.servidor.gestiondeventas.entities.expenses.invoice.InvoiceSupplier;
import com.servidor.gestiondeventas.entities.expenses.outs.dto.SupplierPaymentDTO;
import com.servidor.gestiondeventas.entities.persons.dto.SalesPersonDTO;
import com.servidor.gestiondeventas.entities.persons.dto.SupplierDTO;
import com.servidor.gestiondeventas.entities.products.dto.ProductDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class InvoiceSupplierDTO {
    private Long id;

    private String numberInvoice;

    private SupplierDTO supplier;

    private List<DetailProductInvoiceSupplierDTO> detailProductInvoiceSuppliers = new ArrayList<>();

    private SalesPersonDTO salesPerson;

    private CompanyDTO company;

    private String fiscalStatus;
    private String paymentType;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;
    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date dueDate;



    private Double total;

    static public InvoiceSupplierDTO fromEntity(InvoiceSupplier invoiceSupplier){
        InvoiceSupplierDTO invoiceSupplierDTO = new InvoiceSupplierDTO();

        invoiceSupplierDTO.setSupplier(SupplierDTO.fromSupplierDTO(invoiceSupplier.getSupplier()));
        invoiceSupplierDTO.setId(invoiceSupplier.getId());
        invoiceSupplierDTO.setNumberInvoice(invoiceSupplier.getNumberInvoice());
        invoiceSupplierDTO.setDate(invoiceSupplier.getDate());
        invoiceSupplierDTO.setCompany(CompanyDTO.fromEntity(invoiceSupplier.getCompany()));
        invoiceSupplierDTO.setDueDate(invoiceSupplier.getDueDate());
        invoiceSupplierDTO.setFiscalStatus(invoiceSupplier.getFiscalStatus());
        invoiceSupplierDTO.setPaymentType(invoiceSupplier.getPaymentType());
        invoiceSupplierDTO.setSalesPerson(SalesPersonDTO.fromFamily(invoiceSupplier.getSalesPerson()));
        invoiceSupplierDTO.setTotal(invoiceSupplier.getTotal());
        invoiceSupplierDTO.setDetailProductInvoiceSuppliers(invoiceSupplier.getDetailProductInvoiceSuppliers().stream().map(DetailProductInvoiceSupplierDTO::fromEntity).collect(Collectors.toList()));

        return invoiceSupplierDTO;
    }
}
