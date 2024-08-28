package com.servidor.gestiondeventas.controllers.expenses.invoice;

import com.servidor.gestiondeventas.entities.expenses.invoice.InvoiceSupplier;
import com.servidor.gestiondeventas.entities.expenses.invoice.dto.InvoiceSupplierDTO;
import com.servidor.gestiondeventas.services.expenses.invoice.InvoiceSupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoiceSupplier")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class InvoiceSupplierController {
    private final InvoiceSupplierService invoiceSupplierService;
    @GetMapping
    public ResponseEntity<List<InvoiceSupplierDTO>> getInvoicesSupplier(){
        return new ResponseEntity<>(invoiceSupplierService.getInvoicesSupplier(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InvoiceSupplierDTO> createInvoiceSupplier(){
        return null;
    }
}
