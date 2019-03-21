package may.config.models.jpa;

import may.config.models.PersonaModel;
import may.config.models.PersonaType;
import may.config.models.jpa.entities.PersonaEntity;

import javax.persistence.EntityManager;

public class PersonaAdapter implements PersonaModel, JpaModel<PersonaEntity> {

    private final PersonaEntity persona;

    public PersonaAdapter(PersonaEntity persona) {
        this.persona = persona;
    }

    public static PersonaEntity toEntity(PersonaModel model, EntityManager em) {
        if (model instanceof PersonaAdapter) {
            return ((PersonaAdapter) model).getEntity();
        }
        return em.getReference(PersonaEntity.class, model.getId());
    }

    @Override
    public PersonaEntity getEntity() {
        return persona;
    }

    @Override
    public Long getId() {
        return persona.id;
    }

    @Override
    public PersonaType getType() {
        return persona.type;
    }

    @Override
    public void setType(PersonaType personaType) {
        persona.type = personaType;
    }

    @Override
    public String getName() {
        return persona.name;
    }

    @Override
    public void setName(String name) {
        persona.name = name;
    }

    @Override
    public String getDescription() {
        return persona.description;
    }

    @Override
    public void setDescription(String description) {
        persona.description = description;
    }

}
