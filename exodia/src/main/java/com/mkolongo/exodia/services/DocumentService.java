package com.mkolongo.exodia.services;

import com.mkolongo.exodia.domain.entities.Document;
import com.mkolongo.exodia.domain.models.binding.DocumentRegisterBindingModel;
import com.mkolongo.exodia.domain.models.view.DocumentViewModel;

import java.util.Optional;
import java.util.Set;

public interface DocumentService {

    void scheduleDocument(DocumentRegisterBindingModel bindingModel, String username);

    Optional<String> getDocumentByTitle(String title);

    Document getDocumentById(String id);

    void deleteDocumentById(String id);

    Set<DocumentViewModel> getAllDocumentsByUsername(String username);
}
