package com.servidor.gestiondeventas.controllers.persons;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.*;
import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.entities.persons.dto.CustomerDTO;
import com.servidor.gestiondeventas.services.persons.CustomerService;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@Data
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomer() {
        return new ResponseEntity<>(customerService.getCustomer(), HttpStatus.OK);
    }

    @GetMapping("/{idCustomer}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable Long idCustomer) {
        return new ResponseEntity<>(customerService.getCustomerById(idCustomer), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.editCustomer(customer), HttpStatus.OK);
    }

    @DeleteMapping("/{idCustomer}")
    public String deleteCustomer(@PathVariable Long idCustomer) {
        boolean booleanCustomer = customerService.deleteCustomer(idCustomer);
        if (booleanCustomer) {
            return "Se elimino el cliente con éxito";
        }
        return "El id de este cliente no existe";
    }

    @PostMapping("/name")
    public ResponseEntity<Page<CustomerDTO>> getCustomerByName(
            @RequestBody Customer customer,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        ItemSearchResult itemSearchResult = customerService.getCustomerByName(customer.getName(), page, size);

        List<CustomerDTO> customerDTOList = itemSearchResult.getResultList();
        Long totalElements = itemSearchResult.getTotalElements();

        return new ResponseEntity<>(new PageImpl<>(customerDTOList, PageRequest.of(page, size), totalElements), HttpStatus.OK);
    }

    @GetMapping("/token")
    public ResponseEntity<Message<Customer>> getToken(@RequestHeader(value="Authorization") String authorizationHeader) throws FirebaseAuthException {
        return new ResponseEntity<>(customerService.getToken(authorizationHeader),HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserRecord> createUser (@RequestBody Customer customer) throws FirebaseAuthException {
       return new ResponseEntity<>(customerService.createUser(customer),HttpStatus.CREATED);
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email){
        return new ResponseEntity<>(customerService.getCustomerByEmail(email),HttpStatus.OK);
    }

}
