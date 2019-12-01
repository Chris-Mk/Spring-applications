package com.mkolongo.exodia.services;

import com.mkolongo.exodia.domain.entities.Document;
import com.mkolongo.exodia.domain.entities.User;
import com.mkolongo.exodia.domain.models.binding.DocumentRegisterBindingModel;
import com.mkolongo.exodia.domain.models.view.DocumentViewModel;
import com.mkolongo.exodia.repository.DocumentRepository;
import com.mkolongo.exodia.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void scheduleDocument(DocumentRegisterBindingModel bindingModel, String username) {
        final Document document = modelMapper.map(bindingModel, Document.class);
        userRepository.findUserByUsername(username)
                .ifPresent(document::setUser);

        documentRepository.saveAndFlush(document);
    }

    @Override
    public Optional<String> getDocumentByTitle(String title) {
        return documentRepository.findDocumentByTitle(title)
                .map(Document::getTitle);
    }

    @Override
    public Document getDocumentById(String id) {
        return documentRepository.getOne(id);
    }

    @Override
    public void deleteDocumentById(String id) {
        documentRepository.deleteById(id);
    }

    @Override
    public Set<DocumentViewModel> getAllDocumentsByUsername(String username) {
        final Optional<User> user = userRepository.findUserByUsername(username);

        if (user.isPresent()) {
            final Set<Document> documentByUser = documentRepository.findDocumentByUser(user.get());
            return modelMapper.map(documentByUser, new TypeToken<Set<DocumentViewModel>>() {}.getType());
        }
        return null;
    }
}
