package com.servidor.gestiondeventas;

import com.servidor.gestiondeventas.entities.company.Company;
import com.servidor.gestiondeventas.entities.persons.SalesPerson;
import com.servidor.gestiondeventas.entities.persons.Supplier;

import com.servidor.gestiondeventas.repository.company.CompanyRepository;
import com.servidor.gestiondeventas.repository.persons.SalesPersonRepository;
import com.servidor.gestiondeventas.repository.persons.SupplierRepository;
import com.servidor.gestiondeventas.repository.products.ProductRepository;
import com.servidor.gestiondeventas.repository.products.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Date;

@Configuration
@AllArgsConstructor
public class LoadTable {



}
