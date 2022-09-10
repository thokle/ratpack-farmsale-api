package com.kleistit.farmapi.services.impl;

import com.kleistit.farmapi.nodes.User;
import io.reactivex.Single;

public interface IUserService {

    public Single<User> save(User user);
    public Single<User> getByCredentials(String username, String password);

    public Single<Boolean> doesUserExcist(String username);
}
