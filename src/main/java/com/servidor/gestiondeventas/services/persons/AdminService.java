package com.servidor.gestiondeventas.services.persons;

import com.servidor.gestiondeventas.entities.persons.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    public List<Admin> getAdmin();
    public Optional<Admin> getAdminById(Long idAdmin);
    public Admin createAdmin(Admin admin);
    public Admin editAdmin(Admin admin);
    public boolean deleteAdmin(Long idAdmin);
}
