package com.example.pfa5eme.Service.Impl;

import com.example.pfa5eme.repository.RoleRepository;
import com.example.pfa5eme.repository.UserRepository;
import com.example.pfa5eme.model.Role;
import com.example.pfa5eme.model.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public void initRolesAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        roleDao.save(userRole);

        Role userRole2 = new Role();
        userRole2.setRoleName("ChefDeProjet");
        roleDao.save(userRole2);

        User2 adminUser = new User2();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setRoles(adminRole);

        User2 user = new User2();
        user.setUserName("raj123");
        user.setUserPassword(getEncodedPassword("raj@123"));
        user.setUserFirstName("raj");
        user.setUserLastName("sharma");
        user.setRoles(userRole);
        userDao.save(user);
        userDao.save(adminUser);
        // Userr.setRoles((List<Role>) userRole);

    }



    public List<User2> getUser2() {

        return (List<User2>) userDao.findAll();
    }





    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
