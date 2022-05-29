package com.pucsp.flashcards.business;

import com.pucsp.flashcards.models.Flashcard;
import com.pucsp.flashcards.models.Proficiency;
import com.pucsp.flashcards.repositories.IFlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck implements IDeck {

    @Autowired
    IFlashcardRepository flashcardRepository;

    private Flashcard current;

    private List<Flashcard> flashcardList = new ArrayList<>();

    public Deck() {
        //flashcardList = flashcardRepository.findAllDaily(userId);
    }

    public Flashcard pick() {
        Random r = new Random();
        return flashcardList.get(r.nextInt(0, flashcardList.size()));
    }

    public void share(Integer userId) {

    }

    @Override
    public Flashcard addFlashcard(Flashcard flashcard) {
        return null;
    }

    @Override
    public Long deleteFlashcard(String id) {
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
            this.current.setHits(current.getHits() + 1);
        } else {
            this.current.setHits(0);
        }
        updateProficiency(isCorrect);
        updateLastView();
        flushFlashCard();
    }

    public List<Flashcard> getDeck() {
        return flashcardList;
    }

    private void flushFlashCard() {
        flashcardRepository.save(this.current);
    }

    private void updateLastView() {
        this.current.setLastView(LocalDateTime.now());
    }

    private void updateProficiency(boolean isCorrect) {
        if (isCorrect) {
            if (current.getHits() == 5) {
                this.current.setProficiency(Proficiency.EXPERT);
            } else if (current.getHits() == 2) {
                this.current.setProficiency(Proficiency.INTERMEDIATE);
            }
        } else {
            this.current.setProficiency(Proficiency.BEGINNER);
        }
    }


}
