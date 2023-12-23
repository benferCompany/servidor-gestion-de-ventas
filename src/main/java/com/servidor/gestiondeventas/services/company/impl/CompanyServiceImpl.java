package com.servidor.gestiondeventas.services.company.impl;

import com.servidor.gestiondeventas.entities.company.Company;
import com.servidor.gestiondeventas.repository.company.CompanyRepository;
import com.servidor.gestiondeventas.services.company.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    @Override
    public List<Company> getCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> getCompnayById(Long idCompany) {

        return companyRepository.findById(idCompany);
    }

    @Override
    public Company createCompany(Company company) {

        return companyRepository.save(company);
    }

    @Override
    public Company editCompany(Company company) {

        return companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long idCompany) {
        Optional<Company> companyOptional = companyRepository.findById(idCompany);

        if (companyOptional.isPresent()) {
            companyRepository.delete(companyOptional.get());
            return true;
        }

        return false;
    }
}
