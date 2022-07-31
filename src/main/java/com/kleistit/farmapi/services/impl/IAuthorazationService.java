package com.kleistit.farmapi.services.impl;




import com.kleistit.farmapi.nodes.User;
import io.reactivex.Single;

import java.util.Optional;
/**
 * Created by thokle on 04/02/2017.
 */
public interface IAuthorazationService {
     Single<User> login(String username, String password);
     Single<User> loginByEmail(String email, String password) throws Exception;

     Single<User> tokenExist(String token);

}
