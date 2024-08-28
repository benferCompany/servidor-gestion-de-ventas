package com.servidor.gestiondeventas.controllers.company;

import com.servidor.gestiondeventas.entities.company.Company;
import com.servidor.gestiondeventas.entities.company.dto.CompanyDTO;
import com.servidor.gestiondeventas.entities.persons.dto.SupplierDTO;
import com.servidor.gestiondeventas.services.company.CompanyService;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "*")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getCompany() {
        List<CompanyDTO> companyDTOs = new ArrayList<>();
        companyDTOs = companyService.getCompany().stream()
                .map(company -> CompanyDTO.fromEntity(company))
                .collect(Collectors.toList());
        return new ResponseEntity<>(companyDTOs, HttpStatus.OK);
    }

    @GetMapping("/{idCompany}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long idCompany) {
        return new ResponseEntity<>(CompanyDTO.fromEntity(companyService.getCompnayById(idCompany).get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return new ResponseEntity<>(companyService.createCompany(company), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CompanyDTO> editCompany(@RequestBody Company company) {
        return new ResponseEntity<>(companyService.editCompany(company), HttpStatus.OK);
    }

    @DeleteMapping("/{idCompany}")
    public String deleteCompany(@PathVariable Long idCompany) {
        boolean booleanCompany = companyService.deleteCompany(idCompany);

        if (booleanCompany) {
            return "La compañia fue eliminada";
        }
        return "Esa compañia no existe";
    }
    @PostMapping("/name")
    public ResponseEntity<Page<CompanyDTO>> getCompanyByName(
            @RequestBody Company company,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    )
    {
        ItemSearchResult itemSearchResult = companyService.getCompanyByName(company.getName(), page, size);

        List<CompanyDTO> companyDTOList = itemSearchResult.getResultList();
        Long totalElements = itemSearchResult.getTotalElements();

        return new ResponseEntity<>(new PageImpl<>(companyDTOList, PageRequest.of(page, size), totalElements), HttpStatus.OK);
    }
}
