package com.pucsp.flashcards.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "flashcards")
@TypeDefs(value = {
        @TypeDef(name = "flashcard", typeClass = Flashcard.class)
})
public class Flashcard implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title", columnDefinition = "varchar(256)")
    private String title;

    @Column(name = "description", columnDefinition = "tinytext")
    private String description;

    @Column(name = "front", columnDefinition = "text")
    private String front;

    @Column(name = "back", columnDefinition = "text")
    private String back;

    @Column(name = "last_view", columnDefinition = "timestamp")
    private LocalDateTime lastView;

    @Column(name = "proficiency")
    private Proficiency proficiency;

    @Column(name = "hits", columnDefinition = "int")
    private int hits;
}
