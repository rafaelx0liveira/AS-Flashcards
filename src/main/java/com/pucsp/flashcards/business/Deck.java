package com.pucsp.flashcards.business;

import com.pucsp.flashcards.models.Flashcard;
import com.pucsp.flashcards.models.Proficiency;
import com.pucsp.flashcards.repositories.IFlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Deck implements IDeck {

    @Autowired
    private IFlashcardRepository flashcardRepository;

    private Flashcard current;

    private List<Flashcard> flashcardList = new ArrayList<>();

    public Deck(int userId) {
    }

    public Flashcard generateInitialDeck() {
        return null;
    }

    public Flashcard pick() {
        return null;
    }

    public void share(Integer userId) {

    }

    @Override
    public Flashcard addFlashcard(Flashcard flashcard) {
        return null;
    }

    @Override
    public Long deleteFlashcard(UUID uuid) {
        return null;
    }

    public Flashcard randomizeFlashcard() {
        return null;
    }

    public String showFront() {
        return current.getFront();
    }

    public String showBack() {
        return current.getBack();
    }

    public void validateAnswer(boolean isCorrect) {
        if (isCorrect) {
            int currentHits = current.getHits();
            if (currentHits == 5) {
                this.current.setProficiency(Proficiency.EXPERT);
            } else if (currentHits == 2) {
                this.current.setProficiency(Proficiency.INTERMEDIATE);
            }
            this.current.setHits(current.getHits() + 1);
        } else {
            this.current.setProficiency(Proficiency.BEGINNER);
            this.current.setHits(0);
        }
        this.current.setLastView(LocalDateTime.now());
    }


}
