package com.pucsp.flashcards.controllers;

import com.pucsp.flashcards.business.Deck;
import com.pucsp.flashcards.business.IDeck;
import com.pucsp.flashcards.models.Flashcard;
import com.pucsp.flashcards.repositories.IFlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/decks")
public class DeckController {

    //@Autowired
    //private IDeck deck;

    @Autowired
    IFlashcardRepository repository;

    @GetMapping("/get-all/{id}")
    public List<Flashcard> getDeck(@PathVariable("id") Integer id) {
        return repository.findAllDaily(id);
    }
    /*
    @GetMapping("/randomize/{id}")
    public Flashcard randomizeFlashcard() {
        return deck.randomizeFlashcard();
    }

    @GetMapping("/pick")
    public Flashcard pickFlashcard() {
        return deck.pick();
    }

    @GetMapping("/front")
    public String showFront() {
        return deck.showFront();
    }

    @GetMapping("/back")
    public String showBack() {
        return deck.showBack();
    }

    @PostMapping("/share/{recipientUserId}")
    public void shareFlashcard(@PathVariable("recipientUserId") Integer id) {
        deck.share(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Flashcard> addFlashcard(@RequestBody Flashcard flashcard) {
        return new ResponseEntity<>(deck.addFlashcard(flashcard), HttpStatus.OK);
    }

    @PostMapping("/validate")
    public void validateAnswer(@RequestBody boolean isCorrect) {
        deck.validateAnswer(isCorrect);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlashcard(@PathVariable("id") UUID uuid) {
        Long affectedRows = deck.deleteFlashcard(uuid);
        return new ResponseEntity<>(affectedRows, HttpStatus.OK);
    }
*/
}