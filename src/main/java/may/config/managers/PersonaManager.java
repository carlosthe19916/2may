package may.config.managers;

import may.config.idm.PersonaRepresentation;
import may.config.models.PersonaModel;
import may.config.models.PersonaProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class PersonaManager {

    @Inject
    PersonaProvider personaProvider;

    public PersonaModel addPersona(PersonaRepresentation representation) {
        PersonaModel persona = personaProvider.addPersona(representation.getName());
        updatePersona(persona, representation);
        return persona;
    }

    public PersonaModel updatePersona(Long id, PersonaRepresentation representation) {
        PersonaModel persona = personaProvider.getPersonaById(id).orElseThrow(NullPointerException::new);
        updatePersona(persona, representation);
        return persona;
    }

    private void updatePersona(PersonaModel model, PersonaRepresentation representation) {
        if (representation.getName() != null) {
            model.setType(representation.getType());
        }
        if (representation.getName() != null) {
            model.setName(representation.getName());
        }
        if (representation.getDescription() != null) {
            model.setDescription(representation.getDescription());
        }
    }

    public void deletePersona(Long id) {
        PersonaModel persona = personaProvider.getPersonaById(id).orElseThrow(NullPointerException::new);
        personaProvider.deletePersona(persona);
    }
}
