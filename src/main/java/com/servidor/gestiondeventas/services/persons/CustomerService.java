package com.servidor.gestiondeventas.services.persons;

import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public List<Customer> getCustomer();

    public Optional<Customer> getCustomerById(Long idCustomer);

    public Customer createCustomer(Customer customer);

    public Customer editCustomer(Customer customer);

    public boolean deleteCustomer(Long idCustomer);

    public ItemSearchResult getCustomerByName(String text, int page, int size);

}
