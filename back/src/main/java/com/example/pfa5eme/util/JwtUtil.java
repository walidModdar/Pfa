package com.example.pfa5eme.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Component
public class JwtUtil {
    private static final String SECRET_KEY = "clear_manage_yourself";
   // private static final int TOKEN_VALIDITY_SECONDS = 3600 * 5;
    private static final long TOKEN_VALIDITY_SECONDS = 3600;
    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = parseClaims(token);
        return claims != null ? claimsTFunction.apply(claims) : null;
    }


    public static Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            // Handle the exception if the token is invalid
            ex.printStackTrace();
            return null;
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String userName = getUserNameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_SECONDS * 1000))
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512))
                .compact();
    }
}