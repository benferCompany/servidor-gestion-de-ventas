package com.servidor.gestiondeventas.entities.expenses.outs.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.expenses.invoice.dto.InvoiceSupplierDTO;
import com.servidor.gestiondeventas.entities.expenses.outs.SupplierPayment;
import com.servidor.gestiondeventas.entities.persons.dto.SalesPersonDTO;
import com.servidor.gestiondeventas.entities.persons.dto.SupplierDTO;
import lombok.Data;

import java.util.Date;
@Data
public class SupplierPaymentDTO {
    private Long id;
    private SupplierDTO supplier;
    private SalesPersonDTO salesPerson;

    @JsonFormat(pattern= "dd/MM/yyyy")
    private Date date;


    private String paymentType;
    private Double payment;
    private InvoiceSupplierDTO invoiceSupplier;

    static public SupplierPaymentDTO fromEntity(SupplierPayment supplierPayment){
        if(supplierPayment==null){
            return null;
        }
        SupplierPaymentDTO supplierPaymentDTO = new SupplierPaymentDTO();
        supplierPaymentDTO.setId(supplierPayment.getId());
        supplierPaymentDTO.setSupplier(SupplierDTO.fromSupplierDTO(supplierPayment.getSupplier()));
        supplierPaymentDTO.setSalesPerson(SalesPersonDTO.fromFamily(supplierPayment.getSalesPerson()));
        supplierPaymentDTO.setDate(supplierPayment.getDate());
        supplierPaymentDTO.setPayment(supplierPayment.getPayment());
        supplierPaymentDTO.setInvoiceSupplier(InvoiceSupplierDTO.fromEntity(supplierPayment.getInvoiceSupplier()));
        supplierPaymentDTO.setPaymentType(supplierPayment.getPaymentType());
        return supplierPaymentDTO;
    }

}
