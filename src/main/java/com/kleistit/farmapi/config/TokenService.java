package com.kleistit.farmapi.config;

import com.google.inject.Singleton;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by thokle on 11/09/2016.
 */

@Singleton
public class TokenService {

	public String generateToken() {
		return UUID.randomUUID().toString();
	}

	public String encryptPassword(byte[] password) throws NoSuchAlgorithmException {
		return Base64.getEncoder().encodeToString(password);
	}

	public byte[] decode(byte[] password) {
		return Base64.getDecoder().decode(password);
	}

}
