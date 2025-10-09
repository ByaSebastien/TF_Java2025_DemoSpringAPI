package be.bstorm.tf_java2025_demospringapi.il.utils;

import be.bstorm.tf_java2025_demospringapi.dl.entities.Role;
import be.bstorm.tf_java2025_demospringapi.dl.entities.User;
import be.bstorm.tf_java2025_demospringapi.il.configs.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final JwtBuilder builder;
    private final JwtParser parser;
    private final JwtConfig config;

    public JwtUtil(JwtConfig config) {
        this.config = config;
        builder = Jwts.builder().signWith(config.secretKey);
        parser = Jwts.parser().verifyWith(config.secretKey).build();
    }

    public String generateToken(User user) {

        return builder
                .subject(user.getUsername())
                .claim("id", user.getId())
                .claim("roles", user.getRoles())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() * config.expireAt))
                .compact();
    }

    public Claims getClaims(String token) {
        return parser.parseSignedClaims(token).getPayload();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Long getUserId(String token) {
        return getClaims(token).get("id", Long.class);
    }

    public List<Role> getRoles(String token) {
        List<?> rawRoles = getClaims(token).get("roles", List.class);
        return rawRoles.stream()
                .map(r -> new Role(r.toString()))
                .toList();
    }

    public boolean isValid(String token) {
        Claims claims = getClaims(token);

        Date now  = new Date();

        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }

}
