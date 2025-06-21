package com.kafka.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kafka.entity.User;
import com.kafka.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${jwt.secret}")
	private String SECRET_KEY;
	
	private SecretKey getSignInKey() {
	    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	public String generateToken(String username) {
		
	User user=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
		Map<String, Object> claims =new HashMap<>();
		claims.put("userId",user.getId());
		claims.put("role", user.getRole().name());
		
		
		return Jwts.builder()
				.setClaims(claims) 
	            .setSubject(username) 
	            .setIssuedAt(new Date()) 
	            .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*12))
	            .signWith(getSignInKey())
	            .compact();

	}
    public Claims getClaims(String token) {
    	return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }
	
    public String extractUsername(String token) {
       return getClaims(token).getSubject();
    		   
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
    
    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

}
