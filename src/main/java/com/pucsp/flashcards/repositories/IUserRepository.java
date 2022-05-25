package com.pucsp.flashcards.repositories;

import com.pucsp.flashcards.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {

}
