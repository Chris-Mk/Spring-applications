package com.mkolongo.exodia.repository;

import com.mkolongo.exodia.domain.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

    Optional<User> findUserByUsername(String username);
}
