package com.servidor.gestiondeventas.entities.balance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.expenses.closing.CashClosing;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Capital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double capital_social;
    private Double the_result;
    private Double profit;
    private Double total;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Argentina/Buenos_Aires")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;




}
