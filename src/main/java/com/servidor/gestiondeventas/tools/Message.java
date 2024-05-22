package com.servidor.gestiondeventas.tools;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message<T> {
 private T entity;
 private String message;
 private String status;


}
