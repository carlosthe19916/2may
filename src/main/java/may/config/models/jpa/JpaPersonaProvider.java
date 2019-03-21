package may.config.models.jpa;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import may.config.models.PersonaModel;
import may.config.models.PersonaProvider;
import may.config.models.jpa.entities.PersonaEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class JpaPersonaProvider implements PersonaProvider {

    @Inject
    EntityManager entityManager;

    @Override
    public PersonaModel addPersona(String name) {
        PersonaEntity persona = new PersonaEntity();
        persona.name = name;
        persona.persist();
        return new PersonaAdapter(persona);
    }

    @Override
    public Optional<PersonaModel> getPersonaById(Long id) {
        PersonaEntity entity = PersonaEntity.findById(id);
        return entity != null ? Optional.of(new PersonaAdapter(entity)) : Optional.empty();
    }

    @Override
    public Optional<PersonaModel> getPersonaByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<PersonaModel> getPersonas(int offset, int limit) {
        Page page = new Page(offset, limit);

        List<PersonaEntity> entities = PersonaEntity.findAll()
                .page(page)
                .list();

        return entities.stream()
                .map(PersonaAdapter::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonaModel> getPersonas(String filterText, int offset, int limit) {
        Page page = new Page(offset, limit);

        Parameters parameters = new Parameters()
                .and("filterText", "%" + filterText.toLowerCase());

        List<PersonaEntity> entities = PersonaEntity.find("select p from PersonaEntity p where lower(p.name) like :filterText", parameters)
                .page(page)
                .list();

        return entities.stream()
                .map(PersonaAdapter::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePersona(PersonaModel persona) {
        PersonaEntity entity = PersonaAdapter.toEntity(persona, entityManager);
        entity.delete();
    }

}
