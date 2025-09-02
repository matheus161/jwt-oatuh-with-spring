package br.com.matheus161.authapi.security;

import br.com.matheus161.authapi.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    public static final String EMISSOR = "Matheus";
    public static final long EXPIRATION = 60 * 60 * 1000;
    public static final String SECRET_KEY = "012345679001234567890987654321012345679001234567890987654321";

    public static MyToken encode(User user) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            String jwtToken = Jwts.builder()
                    .subject(user.getUsername())
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .issuer(EMISSOR)
                    .claim(EMISSOR, key)
                    .signWith(key)
                    .compact();

            return new MyToken(jwtToken);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Authentication decode(HttpServletRequest request) {
        try {
            String header = request.getHeader("Authorization");
            System.out.println("Header: " + header);

            if (header != null) {
                String token = header.replace("Bearer ", "");
                SecretKey key  = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
                JwtParser parser = Jwts.parser().verifyWith(key).build();
                Claims claims = (Claims) parser.parse(token).getPayload();

                String subject = claims.getSubject();
                String issuer = claims.getIssuer();
                Date exp =  claims.getExpiration();

                if (issuer.equals(EMISSOR) &&
                        subject.length() > 0 &&
                        exp.after(new Date(System.currentTimeMillis()))
                ) {
                    return new UsernamePasswordAuthenticationToken(
                            "valido",
                            null,
                            Collections.emptyList()
                    );
                }


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
