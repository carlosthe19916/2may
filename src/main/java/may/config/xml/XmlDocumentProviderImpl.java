package may.config.xml;

import may.config.idm.DocumentRepresentation;
import org.w3c.dom.Document;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class XmlDocumentProviderImpl implements XmlDocumentProvider {
    @Override
    public Document buildDocument(DocumentRepresentation representation) {
        return null;
    }
}
