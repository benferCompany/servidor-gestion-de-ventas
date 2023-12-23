package com.servidor.gestiondeventas.services.persons;

import com.servidor.gestiondeventas.entities.persons.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public List<Customer> getCustomer();
    public Optional<Customer> getCustomerById(Long idCustomer);
    public Customer createCustomer(Customer customer);
    public Customer editCustomer(Customer customer);
    public boolean deleteCustomer(Long idCustomer);
}
