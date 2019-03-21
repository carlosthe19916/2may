package may.config.rest;

import may.config.idm.DocumentRepresentation;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("documents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface DocumentsResource {

    @POST
    @Path("/")
    DocumentRepresentation addDocument(@Valid DocumentRepresentation representation);

    @POST
    @Path("/upload")
    DocumentRepresentation uploadDocument(@Valid DocumentRepresentation representation);

    @PUT
    @Path("/{id}")
    DocumentRepresentation updateDocument(
            @PathParam("id") Long id,
            @Valid DocumentRepresentation representation
    );

    @GET
    @Path("{id}")
    DocumentRepresentation getDocumentById(
            @PathParam("id") Long id
    );

    @GET
    @Path("/{id}/ubl-xml")
    @Produces("application/xml")
    Response downloadUBLXml(
            @PathParam("id") String id
    );

    @GET
    @Path("/{id}/printed-version")
    Response downloadPrintedVersion(
            @Context final HttpServletRequest httpServletRequest,
            @PathParam("id") String id,
            @QueryParam("theme") String theme,
            @QueryParam("locale") String locale,
            @QueryParam("format") @DefaultValue("pdf") String format
    );

    @GET
    @Path("/documents")
    @Produces(MediaType.APPLICATION_JSON)
    Response getDocuments(
            @QueryParam("filterText") String filterText,
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("limit") @DefaultValue("10") int limit
    );

}
