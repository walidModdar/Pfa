package com.example.pfa5eme.Service.Impl;


import com.example.pfa5eme.model.Role;
import com.example.pfa5eme.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
