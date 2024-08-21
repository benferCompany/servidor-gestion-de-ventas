package com.servidor.gestiondeventas.services.persons;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public List<Customer> getCustomer();

    public Optional<Customer> getCustomerById(Long idCustomer);

    public Customer createCustomer(Customer customer);

    public Customer editCustomer(Customer customer);

    public boolean deleteCustomer(Long idCustomer);

    public ItemSearchResult getCustomerByName(String text, int page, int size);

    public Customer getToken(String authorizationHeader) throws FirebaseAuthException;
    public UserRecord createUser (Customer customer) throws FirebaseAuthException;

}
