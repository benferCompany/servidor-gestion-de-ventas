package com.servidor.gestiondeventas.services.company.impl;

import com.servidor.gestiondeventas.entities.company.Company;
import com.servidor.gestiondeventas.entities.company.dto.CompanyDTO;
import com.servidor.gestiondeventas.entities.persons.Supplier;
import com.servidor.gestiondeventas.entities.persons.dto.SupplierDTO;
import com.servidor.gestiondeventas.repository.company.CompanyRepository;
import com.servidor.gestiondeventas.services.company.CompanyService;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.GenericSearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    final private EntityManager entityManager;
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
    public CompanyDTO editCompany(Company company) {

        return CompanyDTO.fromEntity(companyRepository.save(company));
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

    @Override
    public ItemSearchResult getCompanyByName(String text, int page, int size) {
        GenericSearchService<Company> genericSearchService = new GenericSearchService<>(entityManager, Company.class);

        Map<String, Object> searchResult = genericSearchService.getEntitiesBySearchTerms(text, new String[]{"name","business_name"}, page, size);
        List<Company> suppliers = (List<Company>) searchResult.get("resultQuery");
        Long totalElements = (Long) searchResult.get("totalElements");
        List<CompanyDTO> companyDTOList = suppliers.stream()
                .map(CompanyDTO::fromEntity)
                .collect(Collectors.toList());
        return new ItemSearchResult<>(companyDTOList, totalElements);
    }
}
