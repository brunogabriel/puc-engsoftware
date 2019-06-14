package io.github.brunogabriel.apimicroservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by brunogabriel on 2019-06-13.
 */
public class TokenAuthenticationService {

    private static final long EXPIRATION_TIME = 864000000;
    private static final String SECRET = "MySecreteApp";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse response, String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        String token = TOKEN_PREFIX + " " + jwt;
        response.addHeader(HEADER_STRING, token);
        try {
            response.getOutputStream().print(token);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static Authentication getByToken(String token) {
        String user = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        return user != null ? new UsernamePasswordAuthenticationToken(user, null, null) : null;
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        return token != null ? getByToken(token) : null;
    }
}

