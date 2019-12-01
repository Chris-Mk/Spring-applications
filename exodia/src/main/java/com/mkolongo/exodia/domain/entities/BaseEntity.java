package com.mkolongo.exodia.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid-hex")
    @GenericGenerator(name = "uuid-hex", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
}
