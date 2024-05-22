package com.servidor.gestiondeventas.entities.receipts.AFIP;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Data
public class AuthAFIP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cuit;

    @Lob
    private String sign;
    @Lob
    private String token;
}
