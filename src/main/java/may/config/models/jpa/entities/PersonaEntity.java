package may.config.models.jpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import may.config.models.PersonaType;

import javax.persistence.*;

@Entity
@Table(name = "persona")
public class PersonaEntity extends PanacheEntity {

    @Column(name = "name", unique = true)
    public String name;

    @Column(name = "description")
    public String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public PersonaType type;

}
