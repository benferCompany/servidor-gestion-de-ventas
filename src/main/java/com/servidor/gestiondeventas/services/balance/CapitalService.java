package com.servidor.gestiondeventas.services.balance;

import com.servidor.gestiondeventas.entities.balance.Capital;

public interface CapitalService {
    public Capital getCapitalByData();
    public void capitalSumStock(Double total);
}
