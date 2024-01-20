package com.springboot.blog.Security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springboot.blog.exception.BlogAPIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app-jwt-expiration-milliseconds}")
	private long jwtExpirationDate;

	// generate JWT token
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();

		Date currentDate = new Date();

		Date expiryDate = new Date(currentDate.getTime() + jwtExpirationDate);

		String token = Jwts.builder()
//			.setSubject(username)
				.subject(username)
//			.setIssuedAt(new Date())
				.issuedAt(new Date())
//			.setExpiration(expiryDate)
				.expiration(expiryDate).signWith(key()).compact();

		return token;
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	// get username form Jwt token
	public String getUsername(String token) {
		Claims claims = Jwts.parser()
//			.setSigningKey(key())
				.verifyWith((SecretKey) key())
				.build()
//			.parseClaimsJws(token)
				.parseSignedClaims(token)
//			.getBody();
				.getPayload();

		String username = claims.getSubject();
		return username;
	}

	// validate Jwt token
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
//				.setSigningKey(key())
				.verifyWith((SecretKey) key())
				.build()
				.parse(token);

			return true;
		} catch (MalformedJwtException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid Jwt Token");
		} catch(ExpiredJwtException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired Jwt Token");
		} catch(UnsupportedJwtException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported Jwt Token");
		} catch(IllegalArgumentException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
		}
	}
}
