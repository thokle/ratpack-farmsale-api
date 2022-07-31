package com.kleistit.farmapi.services.impl;


import com.google.inject.Inject;

import com.kleistit.farmapi.config.AEService;
import com.kleistit.farmapi.nodes.User;
import io.reactivex.Single;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by thokle on 04/12/2016.
 */

public class AuthorazationService implements IAuthorazationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorazationService.class);

	private Session session;
	private AEService aeService;

	@Inject
	public AuthorazationService(Session session, AEService aeService) {
		this.session = session;
		this.aeService = aeService;
	}

	@Override
	public Single<User> login(String username, String password) {
		Optional<User> userNode = Optional.ofNullable(session.queryForObject(User.class,
				" match (u) where u.username='" + username + "'  return u limit 1", Collections.emptyMap()));
		LOGGER.debug("Log in " + username);
		if (userNode.isPresent()) {
			String encrypt = aeService.decrypt(userNode.get().getPassword());
			LOGGER.debug("Log in " + encrypt);
			if (encrypt.equals(password)) {
				return Single.just(userNode.get());
			}
		}
		return null;

	}

	@Override
	public Single<User> loginByEmail(String email, String password) throws Exception {
		try {
			Optional<User> userNode = Optional.ofNullable(session.queryForObject(User.class,
					" match (u) where u.email='" + email + "'  return u limit 1", Collections.emptyMap()));
			LOGGER.debug("Log in " + email);
			if (userNode.isPresent()) {
				String encrypt = aeService.decrypt(userNode.get().getPassword());
				LOGGER.debug("Log in " + encrypt);
				if (encrypt.equals(password)) {
					return Single.just(userNode.get());
			}
			return Single.never();
		}
		}catch (Exception ex) {
			throw  new Exception(ex.getMessage());
		}
		return Single.never();
	}

	@Override
	public Single<User> tokenExist(String token) {
		Optional<User> userNode = Optional.empty();
		userNode = Optional.ofNullable(session.queryForObject(User.class,
				"match (u) where  u.token='" + token + "' return u", Collections.emptyMap()));
		return Single.just(userNode.get());
	}
}
