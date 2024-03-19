package com.servidor.gestiondeventas.services.receipts;


import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.entities.receipts.dto.DetailsDto;

import java.util.List;
import java.util.Optional;

public interface DetailsService {
    public List<DetailsDto> getDetails();

    public DetailsDto getDetailsById(Long idDetails);

    public Details createDetails(Details details);

    public DetailsDto editDetails(Details details);

    public boolean deleteDetails(Long idDetails);

}
