package com.pfejava.springbootpfe.security;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.pfejava.springbootpfe.common.RoleType;
import com.pfejava.springbootpfe.dao.AppUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {
    private final String CLAIMS_SUBJECT = "sub";
    private final String CLAIMS_CREATED = "created";
    private final String CLAIMS_ROLES = "Role";
	
	@Value("${jwt.expiration}")
	private Long TOKEN_VALIDITY = 604800L;
	
	@Value("${jwt.secret}")
	private String TOKEN_SECRET;
	
    public String generateToken(AppUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Set<String> Userroles = new HashSet<>();
        
        claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
        claims.put(CLAIMS_CREATED, new Date());
        
        if(userDetails.getIsAdmin()) {
        	Userroles.add(RoleType.Names.ADMIN);
        } else {
        	Userroles.add(RoleType.Names.USER);
        }
        
        claims.put(CLAIMS_ROLES,Userroles.toArray());
 
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getSubject();
        }catch (Exception ex) {
            return null;
        }
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
    }

    public boolean isTokenValid(String token, AppUser appUser) {
        String username = getUserNameFromToken(token);

        return (username.equals(appUser.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception ex) {
            claims = null;
        }

        return claims;
    }
}