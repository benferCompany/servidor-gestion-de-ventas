package com.servidor.gestiondeventas.entities.balance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.balance.Capital;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class CapitalDTO {
        private Long id;
        private Double capital_social;
        private Double the_result;
        private Double profit;
        private Double total;
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Argentina/Buenos_Aires")
        @Temporal(TemporalType.TIMESTAMP)
        private Date date;

     public static CapitalDTO fromEntity(Capital capital){
         CapitalDTO capitalDTO = new CapitalDTO();
         capitalDTO.setCapital_social(capital.getCapital_social());
         capitalDTO.setId(capital.getId());
         capitalDTO.setDate(capital.getDate());
         capitalDTO.setTotal(capital.getTotal());
         capitalDTO.setThe_result(capital.getThe_result());
         capitalDTO.setProfit(capital.getProfit());
         return capitalDTO;
     }

}
