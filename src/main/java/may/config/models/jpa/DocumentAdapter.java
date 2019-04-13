package may.config.models.jpa;

import may.config.models.DocumentModel;
import may.config.models.PersonaModel;
import may.config.models.jpa.entities.DocumentEntity;
import may.config.models.jpa.entities.PersonaEntity;

import javax.persistence.EntityManager;

public class DocumentAdapter implements DocumentModel, JpaModel<DocumentEntity> {

    private final DocumentEntity document;

    public DocumentAdapter(DocumentEntity document) {
        this.document = document;
    }

    @Override
    public DocumentEntity getEntity() {
        return document;
    }

    @Override
    public Long getId() {
        return document.id;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

}
