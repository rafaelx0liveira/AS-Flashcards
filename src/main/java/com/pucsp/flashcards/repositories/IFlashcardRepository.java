package com.pucsp.flashcards.repositories;

import com.pucsp.flashcards.models.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFlashcardRepository extends JpaRepository<Flashcard, String> {
    @Query(value = "SELECT " +
            "* " +
            "FROM " +
            "flashcards " +
            "WHERE " +
            "((proficiency=0 AND last_view <= (CURDATE() - INTERVAL 1 DAY))" +
            "OR" +
            "(proficiency=1 AND last_view <= (CURDATE() - INTERVAL 3 DAY))" +
            "OR" +
            "(proficiency=2 AND last_view <= (CURDATE() - INTERVAL 7 DAY)))" +
            "AND user_id=?#{[0]}",
            nativeQuery = true)
    List<Flashcard> findAllDaily(Integer id);

    Optional<Flashcard> findFlashcardByIdAndUserId(String id, Integer userId);

    Optional<List<Flashcard>> findAllFlashcardsByUserId(Integer userId);
}