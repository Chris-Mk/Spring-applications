package com.mkolongo.residentEvil.repository;

import com.mkolongo.residentEvil.domain.entities.Capital;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CapitalRepository extends BaseRepository<Capital, Long> {

    Set<Capital> findCapitalsByNameIn(Set<String> name);
}
