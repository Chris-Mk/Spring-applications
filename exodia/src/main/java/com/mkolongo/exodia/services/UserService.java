package com.mkolongo.exodia.services;

import com.mkolongo.exodia.domain.models.service.RegisterUserServiceModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public interface UserService {
    void registerUser(RegisterUserServiceModel serviceModel) throws InvalidKeySpecException, NoSuchAlgorithmException;

    Optional<String> findUserByUsername(String username);

}
