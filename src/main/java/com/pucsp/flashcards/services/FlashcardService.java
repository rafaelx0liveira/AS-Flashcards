package com.pucsp.flashcards.services;

import com.pucsp.flashcards.models.Flashcard;
import com.pucsp.flashcards.models.Proficiency;
import com.pucsp.flashcards.models.User;
import com.pucsp.flashcards.repositories.IFlashcardRepository;
import com.pucsp.flashcards.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class FlashcardService implements IFlashcardService {
    private Flashcard current;

    private List<Flashcard> flashcardList = new ArrayList<>();

    @Autowired
    private final IFlashcardRepository flashcardRepository;
    @Autowired
    private final IUserRepository userRepository;

    public FlashcardService(IFlashcardRepository flashcardRepository, IUserRepository userRepository) {
        this.flashcardRepository = flashcardRepository;
        this.userRepository = userRepository;
    }

    // CRUD METHODS -------------------------------------------------------

    public Optional<Flashcard> getFlashcard(String flashcardId, Integer userId) {
        var user = getUser(userId);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return flashcardRepository.findFlashcardByIdAndUserId(flashcardId, userId);
    }

    public Optional<List<Flashcard>> getDailyFlashcards(Integer userId) {
        var user = getUser(userId);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(flashcardRepository.findAllDaily(userId));
    }

    public Optional<List<Flashcard>> getAllFlashcardsByUserId(Integer userId) {
        var user = getUser(userId);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return flashcardRepository.findAllFlashcardsByUserId(userId);
    }

    public Optional<Flashcard> createFlashcard(Flashcard flashcard, Integer userId) {
        var user = getUser(userId);
        if (user.isEmpty()) {
            return Optional.empty();
        }

        flashcard.setUser(user.get());
        flashcard.setLastView(LocalDateTime.parse("1970-01-01T01:00:00"));
        flashcard.setHits(0);
        flashcard.setProficiency(Proficiency.BEGINNER);
        return Optional.of(flashcardRepository.save(flashcard));
    }

    public Optional<Flashcard> pickFlashcard(Integer userId) {
        var list = getDailyFlashcards(userId);
        if (list.isPresent()) {
            if (list.get().size() != 0) {
                return Optional.of(randomizeFlashcard(list.get()));
            }
        }
        return Optional.empty();
    }

    public Optional<Flashcard> playFlashcard(String flashcardId, Boolean isCorrect) {

        var retrievedFlashcard = flashcardRepository.findById(flashcardId);

        if (retrievedFlashcard.isPresent()) {
            if (retrievedFlashcard.get().getId().equals(flashcardId)) {
                this.current = retrievedFlashcard.get();
                validateAnswer(isCorrect);
                return Optional.of(flashcardRepository.save(retrievedFlashcard.get()));
            }
        }
        return Optional.empty();
    }

    public Optional<Flashcard> deleteFlashcard(String id) {
        var flashcard = flashcardRepository.findById(id);
        if (flashcard.isEmpty()) {
            return Optional.empty();
        }
        flashcardRepository.deleteById(id);
        return flashcard;
    }

    // BUSINESS METHODS ---------------------------------------------------
    public Flashcard randomizeFlashcard(List<Flashcard> list) {
        Random r = new Random();
        return list.get(r.nextInt((list.size() - 1) + 1));
    }

    private void flushFlashCard() {
        flashcardRepository.save(this.current);
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

    private Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }
}
