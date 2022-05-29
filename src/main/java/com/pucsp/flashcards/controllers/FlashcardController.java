package com.pucsp.flashcards.controllers;

import com.pucsp.flashcards.models.Flashcard;
import com.pucsp.flashcards.models.User;
import com.pucsp.flashcards.repositories.IFlashcardRepository;
import com.pucsp.flashcards.repositories.IUserRepository;
import com.pucsp.flashcards.services.IFlashcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flashcards")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class FlashcardController {

    @Autowired
    private IFlashcardRepository flashcardRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IFlashcardService flashcardService;

    @GetMapping("/{flashcardId}")
    public ResponseEntity<?> getFlashcard(
            @PathVariable("flashcardId") String flashcardId,
            @RequestHeader("user-id") Integer userId) {

        var flashcard = flashcardService.getFlashcard(flashcardId, userId);

        if (flashcard.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flashcard, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Flashcard>> getDailyFlashcards(
            @RequestHeader("user-id") Integer userId) {

        var flashcards = flashcardService.getDailyFlashcards(userId);

        if (flashcards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flashcards.get(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Flashcard> createFlashcard(
            @RequestBody Flashcard flashcard,
            @RequestHeader("user-id") Integer userId) {

        var createdFlashcard = flashcardService.createFlashcard(flashcard, userId);

        if (createdFlashcard.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdFlashcard.get(), HttpStatus.CREATED);
    }

    @PutMapping("/{flashcardId}")
    public ResponseEntity<?> playFlashcard(
            @PathVariable("flashcardId") String flashcardId,
            @RequestHeader("is-correct") Boolean isCorrect) {

        var updatedFlashcard = flashcardService.playFlashcard(flashcardId, isCorrect);

        if (updatedFlashcard.isPresent()) {
            return new ResponseEntity<>(updatedFlashcard, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlashcard(@PathVariable("id") String id) {
        var flashcard = flashcardService.deleteFlashcard(id);
        if (flashcard.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(flashcard, HttpStatus.OK);
    }

    private Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }
}
