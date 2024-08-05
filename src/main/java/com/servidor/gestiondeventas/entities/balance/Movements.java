package com.servidor.gestiondeventas.entities.balance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.servidor.gestiondeventas.entities.expenses.outs.SupplierPayment;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.OutFlows;
import com.servidor.gestiondeventas.entities.receipts.Details;
import jdk.jfr.Timestamp;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Movements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "movements",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Details> details;
    @OneToMany(mappedBy = "movements",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SupplierPayment> supplierPayments;
    @OneToOne(mappedBy = "movements")
    @JsonManagedReference
    private OutFlows outFlows;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Argentina/Buenos_Aires")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}
