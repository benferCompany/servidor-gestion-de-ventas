package com.servidor.gestiondeventas.services.company;

import com.servidor.gestiondeventas.entities.company.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    public List<Company> getCompany();

    public Optional<Company> getCompnayById(Long idCompany);

    public Company createCompany(Company company);

    public Company editCompany(Company company);

    public boolean deleteCompany(Long idCompany);
}
