package may.config.models.utils;

import may.config.idm.DocumentRepresentation;
import may.config.idm.PersonaRepresentation;
import may.config.models.DocumentModel;
import may.config.models.PersonaModel;

public class ModelToRepresentation {

    private ModelToRepresentation() {
        // Util Class
    }

    public static PersonaRepresentation toRepresentation(PersonaModel model) {
        PersonaRepresentation rep = new PersonaRepresentation();
        rep.setId(model.getId());
        rep.setName(model.getName());
        rep.setDescription(model.getDescription());
        rep.setType(model.getType());
        return rep;
    }


    public static DocumentRepresentation toRepresentation(DocumentModel document) {
        return null;
    }
}
