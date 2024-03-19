package com.servidor.gestiondeventas.services.company;

import com.servidor.gestiondeventas.entities.company.Company;
import com.servidor.gestiondeventas.entities.company.dto.CompanyDTO;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    public List<Company> getCompany();

    public Optional<Company> getCompnayById(Long idCompany);

    public Company createCompany(Company company);

    public CompanyDTO editCompany(Company company);

    public boolean deleteCompany(Long idCompany);
    public ItemSearchResult getCompanyByName(String text, int page, int size);
}
