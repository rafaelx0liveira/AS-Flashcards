package com.pucsp.flashcards.controllers;

import com.pucsp.flashcards.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pucsp.flashcards.repositories.IUserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserRepository userRepository;

    @GetMapping()
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Integer id) {
        return userRepository.findById(id).get();
    }

    @PostMapping()
    public ResponseEntity<User> createUser(User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

}
