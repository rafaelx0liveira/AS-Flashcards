package com.pucsp.flashcards.models;

import com.pucsp.flashcards.business.Deck;
import lombok.Data;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@TypeDefs(value = {
        @TypeDef(name = "user", typeClass = User.class)
})
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    private Deck deck;
}
