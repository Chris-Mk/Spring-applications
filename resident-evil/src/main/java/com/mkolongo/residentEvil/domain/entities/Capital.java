package com.mkolongo.residentEvil.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "capitals")
@EqualsAndHashCode(callSuper = false)
public class Capital extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 5, scale = 2)
    private float latitude;

    @Column(nullable = false, precision = 5, scale = 2)
    private float longitude;

}
