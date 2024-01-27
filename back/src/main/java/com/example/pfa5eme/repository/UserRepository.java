package com.example.pfa5eme.repository;


import com.example.pfa5eme.model.User2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User2, String> {


}
