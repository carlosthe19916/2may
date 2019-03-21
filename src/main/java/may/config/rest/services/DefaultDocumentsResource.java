package may.config.rest.services;

import may.config.idm.DocumentRepresentation;
import may.config.managers.DocumentManager;
import may.config.models.DocumentModel;
import may.config.models.DocumentProvider;
import may.config.models.utils.ModelToRepresentation;
import may.config.rest.DocumentsResource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class DefaultDocumentsResource implements DocumentsResource {

    @Inject
    DocumentManager documentManager;

    @Inject
    DocumentProvider documentProvider;

    @Override
    public DocumentRepresentation addDocument(@Valid DocumentRepresentation representation) {
        documentProvider.getDocumentByAssignedId(representation.getAssignedId()).ifPresent(organization -> {
            throw new BadRequestException("Document already registered");
        });

        DocumentModel document = documentManager.addDocument(representation);
        return ModelToRepresentation.toRepresentation(document);
    }

    @Override
    public DocumentRepresentation uploadDocument(@Valid DocumentRepresentation representation) {
        return null;
    }

    @Override
    public DocumentRepresentation updateDocument(Long id, @Valid DocumentRepresentation representation) {
        DocumentModel document = documentProvider.getDocumentById(id).orElseThrow(NotFoundException::new);
        if (document.isReadOnly()) {
            throw new BadRequestException("Read only document");
        }

        document = documentManager.updateDocument(id, representation);
        return ModelToRepresentation.toRepresentation(document);
    }

    @Override
    public DocumentRepresentation getDocumentById(Long id) {
        DocumentModel document = documentProvider.getDocumentById(id).orElseThrow(NotFoundException::new);
        return ModelToRepresentation.toRepresentation(document);
    }

    @Override
    public Response downloadUBLXml(String id) {
        return null;
    }

    @Override
    public Response downloadPrintedVersion(HttpServletRequest httpServletRequest, String id, String theme, String locale, String format) {
        return null;
    }

    @Override
    public Response getDocuments(String filterText, int offset, int limit) {
        return null;
    }

}
