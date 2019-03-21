package may.config.models;

import java.util.List;
import java.util.Optional;

public interface PersonaProvider {

    PersonaModel addPersona(String name);

    Optional<PersonaModel> getPersonaById(Long id);

    Optional<PersonaModel> getPersonaByName(String name);

    List<PersonaModel> getPersonas(int offset, int limit);

    List<PersonaModel> getPersonas(String filterText, int offset, int limit);

    void deletePersona(PersonaModel persona);

}
