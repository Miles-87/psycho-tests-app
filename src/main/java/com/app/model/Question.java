package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "questions")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Quiz> quiz;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Category category;

    @OneToMany(mappedBy = "question")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Answer> answers;
}
