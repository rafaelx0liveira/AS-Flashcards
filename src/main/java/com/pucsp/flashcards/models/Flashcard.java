package com.pucsp.flashcards.models;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "flashcards")
@TypeDefs(value = {
        @TypeDef(name = "flashcard", typeClass = Flashcard.class)
})
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "userid", columnDefinition = "int(16)")
    private int userId;

    @Column(name = "title", columnDefinition = "varchar(256)")
    private String title;

    @Column(name = "description", columnDefinition = "tinytext")
    private String description;

    @Column(name = "front", columnDefinition = "text")
    private String front;

    @Column(name = "back", columnDefinition = "text")
    private String back;

    @Column(name = "lastview", columnDefinition = "timestamp")
    private LocalDateTime lastView;

    @Column(name = "proficiency")
    private Proficiency proficiency;

    @Column(name = "hits", columnDefinition = "int")
    private int hits;
}
