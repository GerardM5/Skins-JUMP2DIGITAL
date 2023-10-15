package com.jump2digital.skins.services;

import com.jump2digital.skins.persistence.entities.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity getUser(long id);
    void updateUser(UserEntity user);
    List<UserEntity> importUsers(List<UserEntity> users);
}
