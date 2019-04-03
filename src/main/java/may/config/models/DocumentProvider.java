package may.config.models;

import java.util.List;
import java.util.Optional;

public interface DocumentProvider {

    DocumentModel addDocument(String name);

    Optional<DocumentModel> getDocumentById(Long id);

    Optional<DocumentModel> getDocumentByAssignedId(Long personaId, String type, String assignedId);

    List<DocumentModel> getDocuments(int offset, int limit);

    List<DocumentModel> getDocuments(String filterText, int offset, int limit);

    void deletePersona(DocumentModel document);

}
