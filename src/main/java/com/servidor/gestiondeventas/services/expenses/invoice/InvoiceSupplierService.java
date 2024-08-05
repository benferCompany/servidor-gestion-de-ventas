package com.servidor.gestiondeventas.services.expenses.invoice;

import com.servidor.gestiondeventas.entities.expenses.invoice.InvoiceSupplier;
import com.servidor.gestiondeventas.entities.expenses.invoice.dto.InvoiceSupplierDTO;

import java.util.List;

public interface InvoiceSupplierService {
    public List<InvoiceSupplierDTO> getInvoicesSupplier();

    public InvoiceSupplierDTO createInvoiceSupplier(InvoiceSupplier invoiceSupplier);

    public Double getInvoiceTotal();

}
