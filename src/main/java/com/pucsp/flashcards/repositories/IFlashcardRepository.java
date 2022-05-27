package com.pucsp.flashcards.repositories;

import com.pucsp.flashcards.models.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFlashcardRepository extends JpaRepository<Flashcard, String> {
    @Query(value = "SELECT " +
            "* " +
            "FROM " +
            "flashcards " +
            "WHERE " +
            "(hits < 3 AND lastView >= (CURDATE() - INTERVAL 1 DAY))" +
            "OR" +
            "(hits >= 3 AND hits<=5 AND lastView >= (CURDATE() - INTERVAL 3 DAY))" +
            "OR" +
            "(hits > 5 AND lastView >= (CURDATE() - INTERVAL 7 DAY))" +
            "AND userid=?#{[0]}",
            nativeQuery = true)
    List<Flashcard> findAllDaily(Integer id);

}