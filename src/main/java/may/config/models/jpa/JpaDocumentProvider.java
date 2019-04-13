package may.config.models.jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import may.config.models.DocumentModel;
import may.config.models.DocumentProvider;
import may.config.models.jpa.entities.DocumentEntity;
import may.config.models.jpa.entities.PersonaEntity;

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
    public Optional<DocumentModel> getDocumentByAssignedId(long personaId, String type, String assignedId) {
        Page page = new Page(0, 2);

        Parameters parameters = new Parameters()
                .and("personaId", personaId)
                .and("type", type)
                .and("assignedId", assignedId);

        List<DocumentEntity> documents = DocumentEntity.find("select d from DocumentEntity d where d.persona.id=:personaId and d.type=:type and d.assignedId=:assignedId", parameters)
                .page(page)
                .list();
        if (documents.size() > 1) {
            throw new IllegalStateException("More than one document found");
        }
        return documents.isEmpty() ? Optional.empty() : Optional.of(new DocumentAdapter(documents.get(0)));
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
