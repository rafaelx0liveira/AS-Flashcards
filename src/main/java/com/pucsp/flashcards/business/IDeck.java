package com.pucsp.flashcards.business;

import com.pucsp.flashcards.models.Flashcard;

import java.util.UUID;

public interface IDeck {

    Flashcard addFlashcard(Flashcard flashcard);

    Long deleteFlashcard(String id);

    Flashcard randomizeFlashcard();

    void share(Integer userId);

    Flashcard pick();

    String showFront();

    String showBack();

    void validateAnswer(boolean isCorrect);

}
