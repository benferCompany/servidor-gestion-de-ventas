package com.servidor.gestiondeventas.services.persons.impl;

import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.repository.persons.CustomerRepository;
import com.servidor.gestiondeventas.services.persons.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long idCustomer) {

        return customerRepository.findById(idCustomer);
    }

    @Override
    public Customer createCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Customer editCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public boolean deleteCustomer(Long idCustomer) {
        Optional<Customer> customerOptional = customerRepository.findById(idCustomer);

        if (customerOptional.isPresent()) {
            customerRepository.delete(customerOptional.get());
            return true;
        }

        return false;
    }
}
