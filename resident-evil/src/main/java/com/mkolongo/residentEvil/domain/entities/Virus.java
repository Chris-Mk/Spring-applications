package com.mkolongo.residentEvil.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "virus")
public class Virus extends BaseEntity {

    @Column(nullable = false, unique = true, length = 10)
    private String name;

    @Column(nullable = false, length = 100, columnDefinition = "text")
    private String description;

    @Column(length = 50, name = "side_effects")
    private String sideEffects;

    @Enumerated(EnumType.STRING)
    private Creator creator;

    @Column(name = "is_deadly")
    private boolean isDeadly;

    @Column(name = "is_curable")
    private boolean isCurable;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Mutation mutation;

    @Column
    private byte turnoverRate;

    @Column
    private byte hoursUntilTurnover;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Magnitude magnitude;

    @Column
    private LocalDate releaseDate;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "virus_capitals",
            joinColumns = @JoinColumn(name = "virus_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "capital_id", referencedColumnName = "id"))
    private Set<Capital> capitals;


}
