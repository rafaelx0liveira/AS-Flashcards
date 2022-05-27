package com.pucsp.flashcards.repositories;

import com.pucsp.flashcards.models.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IFlashcardRepository extends JpaRepository<Flashcard, UUID> {
    @Query(value = "SELECT " +
            "* " +
            "FROM " +
            "flashcards " +
            "WHERE " +
            "(hits < 3 AND lastView >= (CURDATE() - INTERVAL 1 DAY))" +
            "AND" +
            "(hits >= 3 AND hits<=5 AND lastView >= (CURDATE() - INTERVAL 3 DAY))" +
            "AND" +
            "(hits > 5 AND lastView >= (CURDATE() - INTERVAL 7 DAY))" +
            "AND userid=?#{[0]}",
            nativeQuery = true)
    List<Flashcard> findAllDaily(Integer id);

}