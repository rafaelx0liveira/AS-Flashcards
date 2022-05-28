package com.pucsp.flashcards.services;

import com.pucsp.flashcards.models.Flashcard;
import com.pucsp.flashcards.models.Proficiency;
import com.pucsp.flashcards.repositories.IFlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class FlashcardService implements IFlashcardService {

    @Autowired
    private final IFlashcardRepository repository;

    private Flashcard current;

    private List<Flashcard> flashcardList = new ArrayList<>();

    public FlashcardService(IFlashcardRepository flashcardRepository) {
        this.repository = flashcardRepository;
    }

    // CRUD METHODS -------------------------------------------------------

    public Optional<Flashcard> getFlashcard(String id, Integer userId) {
        return repository.findFlashcardByIdAndUserId(id, userId);
    }


    public Flashcard getDailyFlashcards() {
        return null;
    }

    public Flashcard createFlashcard() {
        return null;
    }

    public Flashcard updateFlashcard() {
        return null;
    }

    public Flashcard deleteFlashcard() {
        return null;
    }

    // BUSINESS METHODS ---------------------------------------------------
    public Flashcard pick() {
        Random r = new Random();
        return flashcardList.get(r.nextInt(0, flashcardList.size()));
    }

    private void flushFlashCard() {
        repository.save(this.current);
    }

    private void updateLastView() {
        this.current.setLastView(LocalDateTime.now());
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
