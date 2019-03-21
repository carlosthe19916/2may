package may.config.models;

public interface PersonaModel {

    Long getId();

    PersonaType getType();

    void setType(PersonaType personaType);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

}
