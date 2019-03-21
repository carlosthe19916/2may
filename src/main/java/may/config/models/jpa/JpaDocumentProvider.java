package may.config.models.jpa;

import may.config.models.DocumentModel;
import may.config.models.DocumentProvider;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JpaDocumentProvider implements DocumentProvider {

    @Override
    public DocumentModel addDocument(String name) {
        return null;
    }

    @Override
    public Optional<DocumentModel> getDocumentById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<DocumentModel> getDocumentByAssignedId(String assignedId) {
        return Optional.empty();
    }

    @Override
    public List<DocumentModel> getDocuments(int offset, int limit) {
        return null;
    }

    @Override
    public List<DocumentModel> getDocuments(String filterText, int offset, int limit) {
        return null;
    }

    @Override
    public void deletePersona(DocumentModel document) {

    }
}
