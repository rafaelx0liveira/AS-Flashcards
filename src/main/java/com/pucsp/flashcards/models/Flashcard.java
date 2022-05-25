package com.pucsp.flashcards.models;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "flashcards")
@TypeDefs(value = {
        @TypeDef(name = "flashcard", typeClass = Flashcard.class)
})
public class Flashcard {

    @Id
    @GeneratedValue
    @Type(type = "UUID")
    private UUID id;

    @Column(name = "userid")
    private int userId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "front")
    private String front;

    @Column(name = "back")
    private String back;

    @Column(name = "lastview")
    private LocalDateTime lastView;

    @Column(name = "proficiency")
    private Proficiency proficiency;

    @Column(name = "hits")
    private int hits;
}
