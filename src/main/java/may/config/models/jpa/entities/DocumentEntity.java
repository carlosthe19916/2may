package may.config.models.jpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import may.config.models.PersonaType;

import javax.persistence.*;

@Entity
@Table(name = "document")
public class DocumentEntity extends PanacheEntity {

    @Column(name = "type")
    public String type;

    @Column(name = "assigned_id")
    public String assignedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", foreignKey = @ForeignKey)
    public PersonaEntity persona;

}
