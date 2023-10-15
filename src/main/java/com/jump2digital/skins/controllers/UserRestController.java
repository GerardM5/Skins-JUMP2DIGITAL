package com.jump2digital.skins.controllers;

import com.jump2digital.skins.persistence.entities.UserEntity;
import com.jump2digital.skins.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/import")
    public ResponseEntity<?> importSkins(@RequestBody List<UserEntity> users){
        return ResponseEntity.ok(userService.importUsers(users));
    }

}
