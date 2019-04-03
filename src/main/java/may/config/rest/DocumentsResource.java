package may.config.rest;

import may.config.idm.DocumentRepresentation;
import may.config.managers.DocumentManager;
import may.config.models.DocumentModel;
import may.config.models.DocumentProvider;
import may.config.models.PersonaProvider;
import may.config.models.utils.ModelToRepresentation;
import may.config.rest.DocumentsResource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/personas/{personaId}/documents")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocumentsResource {

    @Inject
    DocumentManager documentManager;

    @Inject
    DocumentProvider documentProvider;

    @POST
    @Path("/")
    public DocumentRepresentation addDocument(
            @PathParam("personaId") Long personaId,
            @Valid DocumentRepresentation representation
    ) {
        documentProvider.getDocumentByAssignedId(personaId, representation.getType(), representation.getAssignedId()).ifPresent(organization -> {
            throw new BadRequestException("Document already exists");
        });

        DocumentModel document = documentManager.addDocument(representation);
        return ModelToRepresentation.toRepresentation(document);
    }

    @POST
    @Path("/upload")
    public DocumentRepresentation uploadDocument(
            @PathParam("personaId") Long personaId,
            @Valid DocumentRepresentation representation
    ) {
        return null;
    }

    @PUT
    @Path("/{id}")
    public DocumentRepresentation updateDocument(
            @PathParam("personaId") Long personaId,
            Long id, @Valid DocumentRepresentation representation
    ) {
        DocumentModel document = documentProvider.getDocumentById(id).orElseThrow(NotFoundException::new);
        if (document.isReadOnly()) {
            throw new BadRequestException("Read only document");
        }

        document = documentManager.updateDocument(id, representation);
        return ModelToRepresentation.toRepresentation(document);
    }

    @GET
    @Path("{id}")
    public DocumentRepresentation getDocumentById(
            @PathParam("personaId") Long personaId,
            @PathParam("id") Long id
    ) {
        DocumentModel document = documentProvider.getDocumentById(id).orElseThrow(NotFoundException::new);
        return ModelToRepresentation.toRepresentation(document);
    }

    @GET
    @Path("/{id}/ubl-xml")
    @Produces("application/xml")
    public Response downloadUBLXml(
            @PathParam("personaId") Long personaId,
            @PathParam("id") String id
    ) {
        return null;
    }

    @GET
    @Path("/{id}/printed-version")
    public Response downloadPrintedVersion(
            @Context final HttpServletRequest httpServletRequest,
            @PathParam("personaId") Long personaId,
            @PathParam("id") String id,
            @QueryParam("theme") String theme,
            @QueryParam("locale") String locale,
            @QueryParam("format") @DefaultValue("pdf") String format
    ) {
        return null;
    }

    @GET
    @Path("/documents")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDocuments(
            @PathParam("personaId") Long personaId,
            @QueryParam("filterText") String filterText,
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("limit") @DefaultValue("10") int limit
    ) {
        return null;
    }

}
