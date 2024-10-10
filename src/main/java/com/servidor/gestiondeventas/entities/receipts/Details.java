package com.servidor.gestiondeventas.entities.receipts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.balance.Movements;
import com.servidor.gestiondeventas.entities.company.Company;
import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.entities.persons.SalesPerson;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberInvoice;
    private String cae;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date caeFchVto;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_person_id")
    private SalesPerson salesPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String paymentType;
    private String fiscalStatus;

    @OneToMany(mappedBy = "details", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetailProduct> detailProductList = new ArrayList<>();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_id")
    private Movements movements;

    private Double discount;
    private Double total;
    private Double costTotal;

    private String idSatePayment;


}
