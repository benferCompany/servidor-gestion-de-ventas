package com.servidor.gestiondeventas.services.persons.impl;

import com.servidor.gestiondeventas.entities.persons.Admin;
import com.servidor.gestiondeventas.repository.persons.AdminRepository;
import com.servidor.gestiondeventas.services.persons.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    @Override
    public List<Admin> getAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> getAdminById(Long idAdmin) {

        return adminRepository.findById(idAdmin);
    }

    @Override
    public Admin createAdmin(Admin admin) {

        return adminRepository.save(admin);
    }

    @Override
    public Admin editAdmin(Admin admin) {

        return adminRepository.save(admin);
    }

    @Override
    public boolean deleteAdmin(Long idAdmin) {
        Optional<Admin> adminOptional = adminRepository.findById(idAdmin);

        if (adminOptional.isPresent()) {
            adminRepository.delete(adminOptional.get());
            return true;
        }

        return false;
    }
}
