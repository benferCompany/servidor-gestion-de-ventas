package com.servidor.gestiondeventas.services.receipts.impl;

import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.entities.receipts.dto.DetailsDto;
import com.servidor.gestiondeventas.repository.receipts.DetailProductsRepository;
import com.servidor.gestiondeventas.repository.receipts.DetailsRepository;
import com.servidor.gestiondeventas.services.expenses.closing.CashClosingService;
import com.servidor.gestiondeventas.services.persons.CustomerService;
import com.servidor.gestiondeventas.services.persons.SalesPersonService;
import com.servidor.gestiondeventas.services.products.ProductService;
import com.servidor.gestiondeventas.services.receipts.AFIP.FECAE.SolicitarService;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.ToolsAFIP;
import com.servidor.gestiondeventas.services.receipts.DetailProductService;
import com.servidor.gestiondeventas.services.receipts.DetailsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class DetailsServiceImpl implements DetailsService {
    @Autowired
    private final DetailsRepository detailsRepository;
    private final CustomerService customerService;
    private final SalesPersonService salesPersonService;
    private final ProductService productService;
    private final DetailProductsRepository detailProductsRepository;
    private final DetailProductService detailProductService;
    private final SolicitarService solicitarService;
    private final ToolsAFIP toolsAFIP;
    private final CashClosingService closingService;

    @Override
    public List<DetailsDto> getDetails() {
        return detailsRepository.findAll().stream()
                .map(DetailsDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DetailsDto getDetailsById(Long idDetails) {
        if(detailsRepository.findById(idDetails).isPresent()){
            return DetailsDto.fromEntity(detailsRepository.findById(idDetails).get());
        }
        return null;
    }

    @Override
    public Details createDetails(Details details) throws IOException {
       
        return detailsRepository.save(detailProductService.createDetailProduct(details));




    }


    @Override
    public DetailsDto editDetails(Details details) {
        if(detailsRepository.findById(details.getId()).isPresent()){
            customerService.editCustomer(details.getCustomer());
            salesPersonService.editSalesPerson(details.getSalesPerson());
            return DetailsDto.fromEntity(detailsRepository.save(details));
        }
        return null;

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
    @Override
    public List<Details> getDetailsDateAfter(){

        return detailsRepository.findByDateAfter(closingService.getDateCashClosing());
    }
}
