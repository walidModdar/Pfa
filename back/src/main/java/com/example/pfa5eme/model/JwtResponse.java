package com.example.pfa5eme.model;

public class JwtResponse {

    private User2 user;
    private String jwtToken;

    public JwtResponse(User2 user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public User2 getUser() {
        return user;
    }

    public void setUser(User2 user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
