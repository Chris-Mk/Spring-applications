package com.mkolongo.exodia.repository;

import com.mkolongo.exodia.domain.entities.Document;
import com.mkolongo.exodia.domain.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DocumentRepository extends BaseRepository<Document, String> {

    Optional<Document> findDocumentByTitle(String title);

    Set<Document> findDocumentByUser(User user);
}
