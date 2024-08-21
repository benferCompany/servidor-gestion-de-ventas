package com.servidor.gestiondeventas.controllers.persons;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.entities.persons.dto.CustomerDTO;
import com.servidor.gestiondeventas.services.persons.CustomerService;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/customer")
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
    public FirebaseToken getToken(@RequestHeader(value="Authorization") String authorizationHeader) throws FirebaseAuthException {
        String token = authorizationHeader.replace("Bearer ", "");
        return FirebaseAuth.getInstance().verifyIdToken(token);
    }

    @PostMapping("/createUser")
    public UserRecord createUser (@RequestBody Customer customer) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()

                .setEmail(customer.getEmail())
                .setPassword("benfer");

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        return userRecord;
    }
}
