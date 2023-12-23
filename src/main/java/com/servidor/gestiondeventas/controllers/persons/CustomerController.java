package com.servidor.gestiondeventas.controllers.persons;

import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.services.persons.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomer(){
        return new ResponseEntity<>(customerService.getCustomer(), HttpStatus.OK);
    }

    @GetMapping("/{idCustomer}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable Long idCustomer){
        return new ResponseEntity<>(customerService.getCustomerById(idCustomer), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.editCustomer(customer),HttpStatus.OK);
    }
    @DeleteMapping
    public String deleteCustomer(@PathVariable Long idCustomer){
        boolean booleanCustomer = customerService.deleteCustomer(idCustomer);
        if(booleanCustomer){
            return "Se elimino el cliente con éxito";
        }
        return "El id de este cliente no existe";
    }
}
