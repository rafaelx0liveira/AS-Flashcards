package com.pucsp.flashcards.repositories;

import com.pucsp.flashcards.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
}