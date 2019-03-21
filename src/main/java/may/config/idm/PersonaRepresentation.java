package may.config.idm;

import may.config.models.PersonaType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonaRepresentation {

    @Min(1)
    private Long id;

    private PersonaType type;

    @NotNull
    @Size(min = 3, max = 120)
    private String name;

    @Size(max = 255)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonaType getType() {
        return type;
    }

    public void setType(PersonaType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
