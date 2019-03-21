package may.config.rest;

import may.config.idm.PersonaRepresentation;
import may.config.managers.PersonaManager;
import may.config.models.PersonaModel;
import may.config.models.PersonaProvider;
import may.config.models.utils.ModelToRepresentation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

@Path("personas")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonasResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    PersonaManager personaManager;

    @Inject
    PersonaProvider personaProvider;

    @POST
    @Path("/")
    public Response addPersona(
            @Valid PersonaRepresentation representation
    ) {
        personaProvider.getPersonaByName(representation.getName()).ifPresent(organization -> {
            throw new BadRequestException("Persona already registered");
        });

        PersonaModel persona = personaManager.addPersona(representation);
        return Response.created(uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(persona.getId()))
                .build()
        ).entity(ModelToRepresentation.toRepresentation(persona)).build();
    }

    @GET
    @Path("/")
    public List<PersonaRepresentation> getPersonas(
            @QueryParam("filterText") String filterText,
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("limit") @DefaultValue("10") int limit
    ) {
        if ("*".equals(filterText)) {
            filterText = null;
        }

        List<PersonaModel> personas;
        if (filterText != null && !filterText.trim().isEmpty()) {
            personas = personaProvider.getPersonas(filterText, offset, limit);
        } else {
            personas = personaProvider.getPersonas(offset, limit);
        }

        return personas.stream()
                .map(ModelToRepresentation::toRepresentation)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public PersonaRepresentation getPersonaById(
            @PathParam("id") Long id
    ) {
        PersonaModel persona = personaProvider.getPersonaById(id).orElseThrow(NotFoundException::new);
        return ModelToRepresentation.toRepresentation(persona);
    }

    @PUT
    @Path("/{id}")
    public PersonaRepresentation updatePersona(
            @PathParam("id") Long id,
            @Valid PersonaRepresentation representation
    ) {
        personaProvider.getPersonaById(id).orElseThrow(NotFoundException::new);
        PersonaModel persona = personaManager.updatePersona(id, representation);
        return ModelToRepresentation.toRepresentation(persona);
    }

    @DELETE
    @Path("/{id}")
    public void deletePersona(
            @PathParam("id") Long id
    ) {
        personaProvider.getPersonaById(id).orElseThrow(NotFoundException::new);
        personaManager.deletePersona(id);
    }

}
