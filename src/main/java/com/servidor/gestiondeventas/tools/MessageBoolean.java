package com.servidor.gestiondeventas.tools;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageBoolean {
    private String response;
    private String status;
    private boolean validate;


}