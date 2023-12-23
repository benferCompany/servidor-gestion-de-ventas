package com.servidor.gestiondeventas.services.receipts.impl;

import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.repository.receipts.DetailsRepository;
import com.servidor.gestiondeventas.services.receipts.DetailsService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class DetailsServiceImpl implements DetailsService {
    DetailsRepository detailsRepository;

    @Override
    public List<Details> getDetails() {
        return detailsRepository.findAll();

    }

    @Override
    public Optional<Details> getDetailsById(Long idDetails) {

        return detailsRepository.findById(idDetails);

    }

    @Override
    public Details createDetails(Details details) {

        return detailsRepository.save(details);
    }

    @Override
    public Details editDetails(Details details) {

        return detailsRepository.save(details);
    }

    @Override
    public boolean deleteDetails(Long idDetails) {
        Optional<Details> details = detailsRepository.findById(idDetails);
        if (details.isPresent()) {
            detailsRepository.deleteById(idDetails);
            return true;
        }
        return false;
    }
}
