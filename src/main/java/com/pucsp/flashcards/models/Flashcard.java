package com.pucsp.flashcards.models;

import lombok.Getter;
import lombok.NonNull;
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

    @Column(name = "front", nullable = false, columnDefinition = "text")
    private String front;

    @Column(name = "back", nullable = false, columnDefinition = "text")
    private String back;

    @Column(name = "last_view", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime lastView;

    @Column(name = "proficiency", nullable = false)
    private Proficiency proficiency;

    @Column(name = "hits", nullable = false, columnDefinition = "int")
    private int hits;
}
