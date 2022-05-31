package com.pucsp.flashcards.services;

import com.pucsp.flashcards.models.Flashcard;

import java.util.List;
import java.util.Optional;

public interface IFlashcardService {

    Optional<Flashcard> getFlashcard(String flashcardId, Integer userId);

    Optional<List<Flashcard>> getDailyFlashcards(Integer userId);

    Optional<Flashcard> pickFlashcard(Integer userId);

    Optional<List<Flashcard>> getAllFlashcardsByUserId(Integer userId);

    Optional<Flashcard> createFlashcard(Flashcard flashcard, Integer userId);

    Optional<Flashcard> playFlashcard(String flashcardId, Boolean isCorrect);

    Optional<Flashcard> deleteFlashcard(String id);
}
