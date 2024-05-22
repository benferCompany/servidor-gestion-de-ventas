package com.servidor.gestiondeventas.services.receipts.impl;

import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.receipts.DetailProduct;
import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.entities.receipts.dto.DetailsDto;
import com.servidor.gestiondeventas.repository.receipts.DetailProductsRepository;
import com.servidor.gestiondeventas.repository.receipts.DetailsRepository;
import com.servidor.gestiondeventas.services.persons.CustomerService;
import com.servidor.gestiondeventas.services.persons.SalesPersonService;
import com.servidor.gestiondeventas.services.products.ProductService;
import com.servidor.gestiondeventas.services.receipts.DetailProductService;
import com.servidor.gestiondeventas.services.receipts.DetailsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    public Details createDetails(Details details) {
        Details newDetails = new Details();

        newDetails.setPaymentType(details.getPaymentType());
        newDetails.setDate(new Date());
        newDetails.setCustomer(details.getCustomer());
        newDetails.setFiscalStatus(details.getFiscalStatus());
        newDetails.setSalesPerson(details.getSalesPerson());
        newDetails.setDetailProductList(details.getDetailProductList());

        Details returnDetails = detailsRepository.save(newDetails);
        detailProductService.createDetailProduct(returnDetails);
        return returnDetails;
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
}
