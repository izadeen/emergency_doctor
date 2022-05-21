package com.example.emergency_doctor.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.stream;

@Component
public class Jwt {

    /**
     * @param subject username or sensor id
     * @param roles user authorities
     * @return token
     */
    public static String jwt_genrator(String subject, List<String> roles) {
        // secret is must be a secret key (save it somewhere safe)
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        return JWT.create().withSubject(subject).withClaim("roles", roles).sign(algorithm);
    }

    /**
     * @param token
     * @return if the token is valid
     * @throws Exception
     */
    public static UsernamePasswordAuthenticationToken jwtIsValid(String token) throws Exception {

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles)
                .forEach(
                        role -> {
                            authorities.add(new SimpleGrantedAuthority(role));
                        });
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
