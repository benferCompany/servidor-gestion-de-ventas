package com.servidor.gestiondeventas.services.balance;

import com.servidor.gestiondeventas.entities.balance.Active;

public interface ActiveService {
    public Active getActiveByDate();
    public Active getActive();
}
