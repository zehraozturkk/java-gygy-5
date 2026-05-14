package com.turkcell.spring_cqrs.core.security.jwt;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
@EnableConfigurationProperties(JwtProperties.class)
public class JwtService {
    private final JwtProperties jwtProperties;
    private final SecretKey signingKey;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;

        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generate(UUID userId, String email, List<String> roles)
    {
        Instant now = Instant.now();
        return Jwts.builder() // Jwts. 'dan sonra gelen her yeni fonksiypn birer claimdir.'
                   .issuer(this.jwtProperties.getIssuer())
                   .subject(userId.toString())
                   .claim("email", email)
                   .claim("roles", roles)
                   .issuedAt(Date.from(now))
                   .expiration(Date.from(now.plusSeconds(this.jwtProperties.getExpirationInSeconds())))
                   .signWith(this.signingKey)
                   .compact();
    }

    public String extractUserId(String token)
    {
        return extractClaim(token, Claims::getSubject); // subject claim'ini almak için Claims::getSubject fonksiyonunu kullanıyoruz.
    }

    public String extractEmail(String token)
    {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token)
    {
        return extractClaim(token, claims -> claims.get("roles", List.class));
    }

    public boolean isTokenValid(String token)
    {
        try {
            return !extractClaim(token, Claims::getExpiration).before(new Date());
        }catch(Exception e){
            return false;
        }
    }

    // burada tokeni parse ederken, claimleri almak icin kullanilan ortak bir method yazdik.
    //  Bu method, tokeni parse eder ve verilen resolver fonksiyonunu kullanarak claimleri alir.
    // eğer bunu yapmasaydım claimleri tekrar tekrar almak için aynı kodu tekrar yazmak zorunda kalırdım.
    private <T> T extractClaim(String token, Function<Claims, T> resolver) // burada "Function<Claims, T> resolver" ifade diyorki bana bi fonk. ver ben o fonk. alime vereyim
    {
        Claims claims = Jwts.parser()
                            .verifyWith(signingKey) // kendi key'in ile okuyor yani güvenli.
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();
        return resolver.apply(claims);
    } 
}
