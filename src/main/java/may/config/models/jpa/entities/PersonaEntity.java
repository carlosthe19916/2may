package may.config.models.jpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import may.config.models.PersonaType;

import javax.persistence.*;

@Entity
@Table(name = "persona")
@NamedQueries(value = {
        @NamedQuery(name = "GetAllPersonasByFilterText", query = "select p from PersonaEntity p where lower(p.name) like :filterText")
})
public class PersonaEntity extends PanacheEntity {

    @Column(unique = true)
    public String name;

    @Column()
    public String description;

    @Enumerated(EnumType.STRING)
    @Column()
    public PersonaType type;

}
