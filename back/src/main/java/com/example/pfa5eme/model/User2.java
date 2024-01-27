package com.example.pfa5eme.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User2 {
    @Id
    @NotEmpty(message = "Password can not b empty")

    private String userName;
    @NotEmpty(message = "Password can not b empty")

    private String userFirstName;
    @NotEmpty(message = " userLastName can not b empty")

    private String userLastName;
    //  @NotEmpty(message = "Password can not b empty")
    private String userPassword;
    // @NotEmpty(message = "geenre can not b empty")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Role roles ;
    public User2(String username,  String password) {
        this.userName = username;

        this.userPassword = password;
    }
}
