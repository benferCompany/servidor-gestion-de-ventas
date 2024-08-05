package com.servidor.gestiondeventas.entities.balance.dto;

import com.servidor.gestiondeventas.entities.balance.Active;
import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.entities.receipts.dto.DetailsDto;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ActiveDTO {
    private Double cash;
    private Double account_bank;
    private Double current_account;
    private Double value_store;
    private Double total;

    public static ActiveDTO fromEntity(Active active){
        if (active == null) {
            return null;
        }
        ActiveDTO activeDTO = new ActiveDTO();

        activeDTO.setCash(active.getCash());
        activeDTO.setAccount_bank(active.getAccount_bank());
        activeDTO.setValue_store(active.getValue_store());
        activeDTO.setCurrent_account(active.getCurrent_account());
        activeDTO.setTotal(active.getTotal());
        return  activeDTO;

    }
}
