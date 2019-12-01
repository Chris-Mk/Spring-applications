package com.mkolongo.exodia.services;

import com.mkolongo.exodia.domain.entities.User;
import com.mkolongo.exodia.domain.models.service.RegisterUserServiceModel;
import com.mkolongo.exodia.repository.UserRepository;
import com.mkolongo.exodia.util.PasswordHash;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(RegisterUserServiceModel serviceModel) throws InvalidKeySpecException, NoSuchAlgorithmException {
        final User user = modelMapper.map(serviceModel, User.class);
        user.setPassword(PasswordHash.hashPassword(serviceModel.getPassword()));
        userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<String> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .map(User::getUsername);
    }
}
