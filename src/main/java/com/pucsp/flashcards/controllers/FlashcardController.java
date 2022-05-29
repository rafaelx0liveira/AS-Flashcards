package com.pucsp.flashcards.controllers;

import com.pucsp.flashcards.models.Flashcard;
import com.pucsp.flashcards.models.User;
import com.pucsp.flashcards.repositories.IFlashcardRepository;
import com.pucsp.flashcards.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlashcard(
            @PathVariable("id") String id,
            @RequestHeader("user-id") Integer userId) {

        var user = getUser(userId);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var flashcard = flashcardRepository.findFlashcardByIdAndUserId(id, userId);
        if (flashcard.isPresent()) {
            return new ResponseEntity<>(flashcard, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<Flashcard>> getDailyFlashcards(@RequestHeader("user-id") Integer userId) {
        var user = getUser(userId);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(flashcardRepository.findAllDaily(userId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Flashcard> createFlashcard(
            @RequestBody Flashcard flashcard,
            @RequestHeader("user-id") Integer userId) {

        var user = getUser(userId);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        flashcard.setUser(user.get());
        flashcard.setLastView(LocalDateTime.now(ZoneId.of("UTC")));

        return new ResponseEntity<>(flashcardRepository.save(flashcard), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateFlashcard(@RequestBody Flashcard updatedFlashcard) {

        var retrievedFlashcard = flashcardRepository.findById(updatedFlashcard.getId());

        if (retrievedFlashcard.isPresent()) {
            if (retrievedFlashcard.get().getId().equals(updatedFlashcard.getId())) {
                updatedFlashcard.setLastView(LocalDateTime.now(ZoneId.of("UTC")));
                return new ResponseEntity<>(flashcardRepository.save(updatedFlashcard), HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlashcard(@PathVariable("id") String id) {

        var flashcard = flashcardRepository.findById(id);

        if (flashcard.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        flashcardRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }
}
