package com.servidor.gestiondeventas.entities.receipts;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class PurchaseInvoice extends Receipts {
    @Column
    private String type;
    @OneToOne(mappedBy = "purchaseInvoice")
    private DispatcheNote dispatchNote;

    @OneToOne(mappedBy = "purchaseInvoice")
    private Details details;


}
