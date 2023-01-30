package com.nttdata.evaluacion.restapi.security;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(String username,String email) {
		Date now = new Date();
		String jwtToken = Jwts.builder()
			.claim("name", username)
			.claim("email", email)
			.setSubject(username)
			.setId(UUID.randomUUID().toString())
			.setIssuedAt(now)
			.setExpiration(addMinutes(now,60))
			.compact();

			return jwtToken;
	}

	public Date addMinutes(Date fecha, int minutes){
		Calendar date = Calendar.getInstance();
		long timeInSecs = date.getTimeInMillis();
		Date afterAddingMins = new Date(timeInSecs + (minutes * 60 * 1000));
		return afterAddingMins;
	}
}