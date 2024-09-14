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
    private final SalesPersonRepository salesPersonRepository;
    private final SupplierRepository supplierRepository;
    private final CompanyRepository companyRepository;

    @PostConstruct
    @Transactional
    public void init(){
        SalesPerson salesPerson = new SalesPerson();
        salesPerson.setName("Vendedor");
        salesPerson.setLast_name("Apellido Vendedor");
        salesPerson.setAuth("");
        salesPerson.setImg("https://img.freepik.com/fotos-premium/retrato-hombre-negocios-expresion-cara-seria-fondo-estudio-espacio-copia-bengala-persona-corporativa-enfoque-pensamiento-duda-mirada-facial-dilema-o-concentracion_590464-84924.jpg");
        salesPerson.setShift("");
        salesPerson.setCommission(0);
        salesPerson.setAddress("Dirección de Vendedor");
        salesPerson.setIdPersonal("11-1111111111-1");
        salesPerson.setEmail("email@vendedor.com");
        salesPerson.setMobile_phone("1111111");
        salesPerson.setPhone("111111");

        salesPersonRepository.save(salesPerson);

        Supplier supplier = new Supplier();
        supplier.setName("Nombre de Proveedor");
        supplier.setLast_name("Apellido de Proveedor");
        supplier.setAddress("Dirección de Proveedor");
        supplier.setEmail("email@proveedor.com");
        supplier.setPhone("111111111");
        supplier.setName_bussiness("Empresa Proveedor");
        supplier.setIdPersonal("11-111111111-11");
        supplier.setMobile_phone("111111111111");

        supplierRepository.save(supplier);

        Company company = new Company();

        company.setName("Empresa");
        company.setAddress("Dirección de Empresa");
        company.setCuit("11-111111111-1");
        company.setBusiness_activity(new Date());
        company.setBusiness_name("Nombre de Empreas");
        company.setFiscal_status("Monotributo");

        Company companySave=companyRepository.save(company);



    }


}
