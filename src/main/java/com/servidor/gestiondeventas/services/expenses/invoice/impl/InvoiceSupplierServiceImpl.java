package com.servidor.gestiondeventas.services.expenses.invoice.impl;

import com.servidor.gestiondeventas.entities.expenses.invoice.InvoiceSupplier;
import com.servidor.gestiondeventas.entities.expenses.invoice.dto.InvoiceSupplierDTO;
import com.servidor.gestiondeventas.repository.company.CompanyRepository;
import com.servidor.gestiondeventas.repository.expenses.invoice.InvoiceSupplierRepository;
import com.servidor.gestiondeventas.repository.persons.SalesPersonRepository;
import com.servidor.gestiondeventas.repository.persons.SupplierRepository;
import com.servidor.gestiondeventas.services.expenses.closing.CashClosingService;
import com.servidor.gestiondeventas.services.expenses.invoice.InvoiceSupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceSupplierServiceImpl implements InvoiceSupplierService {
    private final InvoiceSupplierRepository invoiceSupplierRepository;
    private final CashClosingService cashClosingService;
    private final SupplierRepository supplierRepository;
    private final CompanyRepository companyRepository;
    private final SalesPersonRepository salesPersonRepository;

    @Override
    public List<InvoiceSupplierDTO> getInvoicesSupplier() {

        return invoiceSupplierRepository.findAll().stream().map(InvoiceSupplierDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public InvoiceSupplierDTO createInvoiceSupplier(InvoiceSupplier invoiceSupplier){
        invoiceSupplier.setSupplier(supplierRepository.getById(invoiceSupplier.getSupplier().getId()));
        invoiceSupplier.setCompany(companyRepository.getById(invoiceSupplier.getCompany().getId()));
        invoiceSupplier.setSalesPerson(salesPersonRepository.getById(invoiceSupplier.getSalesPerson().getId()));
        invoiceSupplier.setDate(new Date());
        return InvoiceSupplierDTO.fromEntity(invoiceSupplier);
    }

    @Override
    public Double getInvoiceTotal() {
        return invoiceSupplierRepository.findByDateAfter(cashClosingService.getDateCashClosing()).stream().mapToDouble(InvoiceSupplier::getTotal).sum();
    }
}
