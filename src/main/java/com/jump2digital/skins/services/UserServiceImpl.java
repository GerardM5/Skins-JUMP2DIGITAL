package com.jump2digital.skins.services;

import com.jump2digital.skins.persistence.entities.UserEntity;
import com.jump2digital.skins.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUser(long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            return userEntityOptional.get();
        } else throw new NoSuchElementException("No se encontró el usuario con el ID: " + id);
    }

    @Override
    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }



    @Override
    public List<UserEntity> importUsers(List<UserEntity> users) {
        return userRepository.saveAll(users);
    }
}
