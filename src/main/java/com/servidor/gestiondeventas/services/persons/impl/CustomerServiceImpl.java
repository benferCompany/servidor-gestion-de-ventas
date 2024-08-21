package com.servidor.gestiondeventas.services.persons.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.entities.persons.dto.CustomerDTO;
import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.dto.ProductDTO;
import com.servidor.gestiondeventas.repository.persons.CustomerRepository;
import com.servidor.gestiondeventas.services.persons.CustomerService;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.GenericSearchService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.*;

@Service
@AllArgsConstructor

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private final EntityManager entityManager;
    
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
    @Override
    public ItemSearchResult getCustomerByName(String text, int page, int size){
        GenericSearchService<Customer> genericSearchService = new GenericSearchService<>(entityManager, Customer.class);

        Map<String, Object> searchResult = genericSearchService.getEntitiesBySearchTerms(text, new String[]{"name","last_name"}, page, size);
        List<Customer> products = (List<Customer>) searchResult.get("resultQuery");
        Long totalElements = (Long) searchResult.get("totalElements");
        List<CustomerDTO> productDTOList = products.stream()
                .map(CustomerDTO::fromEntity)
                .collect(Collectors.toList());
        return new ItemSearchResult<>(productDTOList, totalElements);
    }

    @Override
    public Customer getToken(String authorizationHeader) throws FirebaseAuthException {
        String token = authorizationHeader.replace("Bearer ", "");
        FirebaseToken user = FirebaseAuth.getInstance().verifyIdToken(token);
        if(user.isEmailVerified()){
            return customerRepository.findByEmail(user.getEmail()).orElseGet(()->{
                Customer cus = new Customer();
                cus.setEmail(user.getEmail());
                return customerRepository.save(cus);
            });

        }
      return null;
    }

    @Override
    public UserRecord createUser(Customer customer) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(customer.getEmail())
                .setPassword("benfer");
        return FirebaseAuth.getInstance().createUser(request);

    }
}
