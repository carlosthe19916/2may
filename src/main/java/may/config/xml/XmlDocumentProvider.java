package may.config.xml;

import may.config.idm.DocumentRepresentation;
import org.w3c.dom.Document;

public interface XmlDocumentProvider {

    Document buildDocument(DocumentRepresentation representation);

}
