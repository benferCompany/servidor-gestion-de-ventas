package com.servidor.gestiondeventas.services.persons.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.entities.persons.dto.CustomerDTO;
import com.servidor.gestiondeventas.repository.persons.CustomerRepository;
import com.servidor.gestiondeventas.services.persons.CustomerService;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.GenericSearchService;

import com.servidor.gestiondeventas.tools.Message;
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
        Optional<Customer> customer1 = customerRepository.findById(customer.getId());
        if(customer1.isPresent()){
            return customerRepository.save(customer);
        }
        return  null;

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
    public Message<Customer> getToken(String authorizationHeader) throws FirebaseAuthException {
        String token = authorizationHeader.replace("Bearer ", "");
        FirebaseToken user = FirebaseAuth.getInstance().verifyIdToken(token);
        Message<Customer> message = new Message<Customer>();
        if(user.isEmailVerified()){
            Optional<Customer> customer = customerRepository.findByEmail(user.getEmail());
            if(customer.isPresent()){
                message.setStatus("validate");
                message.setEntity(customer.get());
                message.setMessage("El usuario se encuentra entre nuestros clientes");

            }else{
                Customer nwcustomer = new Customer();
                nwcustomer.setEmail(user.getEmail());
                nwcustomer.setName(user.getName());
                message.setEntity(customerRepository.save(nwcustomer));
                message.setStatus("pending");
                message.setMessage("El usuario es nuevo falta llenar datos");
            }


        }else {
            message.setStatus("error");
            message.setMessage("Ocurrio un error inesperado");
        }
      return message;
    }

    @Override
    public UserRecord createUser(Customer customer) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(customer.getEmail())
                .setPassword("benfer");
        return FirebaseAuth.getInstance().createUser(request);

    }
}
