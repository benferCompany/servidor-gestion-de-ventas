package com.servidor.gestiondeventas.entities.expenses.closing;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.balance.Active;
import com.servidor.gestiondeventas.entities.balance.Capital;
import com.servidor.gestiondeventas.entities.balance.Movements;
import com.servidor.gestiondeventas.entities.balance.Passive;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class CashClosing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "active_id")
    private Active active;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "passive_id")
    private Passive passive;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "capital_id")
    private Capital capital;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name ="movement_id")
    private Movements movements;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Argentina/Buenos_Aires")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOpen;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Argentina/Buenos_Aires")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClose;


}
