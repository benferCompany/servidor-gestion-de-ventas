package com.servidor.gestiondeventas.services.receipts;


import com.servidor.gestiondeventas.entities.receipts.Details;

import java.util.List;
import java.util.Optional;

public interface DetailsService {
    public List<Details> getDetails();

    public Optional<Details> getDetailsById(Long idDetails);

    public Details createDetails(Details details);

    public Details editDetails(Details details);

    public boolean deleteDetails(Long idDetails);

}
