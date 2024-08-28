package com.servidor.gestiondeventas.controllers.persons;

import com.servidor.gestiondeventas.entities.persons.Admin;
import com.servidor.gestiondeventas.services.persons.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping
    public ResponseEntity<List<Admin>> getAdmin() {
        return new ResponseEntity<>(adminService.getAdmin(), HttpStatus.OK);
    }

    @GetMapping("/{idAdmin}")
    public ResponseEntity<Optional<Admin>> getAdminById(@PathVariable Long idAdmin) {
        return new ResponseEntity<>(adminService.getAdminById(idAdmin), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.createAdmin(admin), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Admin> editAdmin(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.editAdmin(admin), HttpStatus.OK);
    }

    @DeleteMapping
    public String deleteAdmin(@PathVariable Long idAdmin) {
        boolean booleanAdmin = adminService.deleteAdmin(idAdmin);
        if (booleanAdmin) {
            return "Se elimino el admin con éxito";
        }
        return "El id de este admin no existe";
    }
}
