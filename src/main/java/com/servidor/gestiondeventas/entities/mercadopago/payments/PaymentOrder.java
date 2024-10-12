package com.servidor.gestiondeventas.entities.mercadopago.payments;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class PaymentOrder {
    @Id
    private String id;
    private String status;
    private String statusDetail;
    @OneToMany(mappedBy = "paymentOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)    
    private List<Items> items;
}
