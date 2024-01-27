package com.example.pfa5eme.Service.Impl;


import com.example.pfa5eme.model.JwtRequest;
import com.example.pfa5eme.model.JwtResponse;
import com.example.pfa5eme.model.Role;
import com.example.pfa5eme.model.User2;
import com.example.pfa5eme.repository.RoleRepository;
import com.example.pfa5eme.repository.UserRepository;
import org.springframework.security.core.Authentication;


import com.example.pfa5eme.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleDao;

    public Authentication register(User2 request) {
        Role userRole = new Role();
        userRole.setRoleName("User");
        roleDao.save(userRole);

        var user = User2.builder()
                .userName(request.getUserName())
                .userFirstName(request.getUserFirstName())
                .userLastName(request.getUserLastName())
                .userPassword(getEncodedPassword(request.getUserPassword()))
                .roles(userRole)
                .build();
        userDao.save(user);


//        System.out.println(user);



        return  new UsernamePasswordAuthenticationToken(
                user.getUserName(),
                user.getUserPassword(),
                getAuthority(user)
        );
//        return null;
    }


    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);
        UserDetails userDetails = loadUserByUsername(userName);
        String newGenrtedToken = jwtUtil.generateToken(userDetails);
        User2 user = userDao.findById(userName).get();
        return new JwtResponse(user, newGenrtedToken);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User2 user = userDao.findById(username).get();
        if (user != null) {
            return new User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)

            );
        } else {
            throw new UsernameNotFoundException("Username is not valid ");
        }
    }

    private Set getAuthority(User2 user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoles().getRoleName()));

        return authorities;
    }



    private Authentication authenticate(String userName, String userPassword) throws Exception {
        User2 user = userDao.findById(userName).orElse(null);
        if (user != null && passwordEncoder.matches(userPassword, user.getUserPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    userName,
                    userPassword,
                    getAuthority(user)
            );
        } else {
            throw new Exception("Invalid credentials");
        }
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}