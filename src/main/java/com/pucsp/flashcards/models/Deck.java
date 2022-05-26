package com.pucsp.flashcards.models;

import com.pucsp.flashcards.repositories.IFlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    @Autowired
    private IFlashcardRepository flashcardRepository;

    private Flashcard current;

    private List<Flashcard> flashcardList = new ArrayList<>();

    public Deck(int userId) {
        flashcardList = flashcardRepository.findAllDaily(userId);
    }

    public Flashcard generateInitialDeck() {
        return null;
    }

    public Flashcard pick() {
        Random r = new Random();
        return flashcardList.get(r.nextInt(0, flashcardList.size()));
    }

    public void share(User user) {

    }

    public String showFront() {
        return current.getFront();
    }

    public String showBack() {
        return current.getBack();
    }

    public void validateAnswer(boolean isCorrect) {
        if (isCorrect) {
            this.current.setHits(current.getHits() + 1);
        } else {
            this.current.setHits(0);
        }
        updateProficiency(isCorrect);
        updateLastView();
        flushFlashCard();
    }

    private void flushFlashCard() {
        flashcardRepository.save(this.current);
    }
    private void updateLastView() {
        this.current.setLastView(LocalDateTime.now());
    }
    private void updateProficiency(boolean isCorrect) {
        if(isCorrect) {
            if (current.getHits() == 5) {
                this.current.setProficiency(Proficiency.EXPERT);
            } else if (current.getHits() == 2) {
                this.current.setProficiency(Proficiency.INTERMEDIATE);
            }
        }
        else{
            this.current.setProficiency(Proficiency.BEGINNER);
        }
    }
}
