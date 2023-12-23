package com.servidor.gestiondeventas.entities.receipts;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class DispatcheNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String Address;
    @OneToOne
    @JoinColumn(name = "purchase_invoice_id")
    private PurchaseInvoice purchaseInvoice;

}
