package may.config.managers;

import may.config.files.FileModel;
import may.config.files.FileProvider;
import may.config.idm.DocumentRepresentation;
import may.config.indexer.DocumentIndexer;
import may.config.models.DocumentModel;
import may.config.models.DocumentProvider;
import may.config.models.exceptions.ModelReadOnlyException;
import may.config.xml.XmlDocumentProvider;
import org.w3c.dom.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DocumentManager {

    @Inject
    DocumentProvider documentProvider;

    @Inject
    XmlDocumentProvider xmlDocumentProvider;

    @Inject
    FileProvider fileProvider;

    @Inject
    DocumentIndexer documentIndexer;

    public DocumentModel addDocument(DocumentRepresentation representation) {
        Document xmlDocument = xmlDocumentProvider.buildDocument(representation);
        return addDocument(xmlDocument);
    }

    public DocumentModel addDocument(Document xmlDocument) {
        // Sign document
        xmlDocument = null;

        processXmlDocument(xmlDocument);
        return null;
    }

    public DocumentModel importDocument(Document xmlDocument) {
        processXmlDocument(xmlDocument);
        DocumentModel document = documentProvider.addDocument(null);

        return document;
    }

    private DocumentModel processXmlDocument(Document xmlDocument) {
        // Save xml in storage
        FileModel file = fileProvider.addFile(null, null);

        // Save in index database
        documentIndexer.index(xmlDocument);

        // Guardar en base de datos
        DocumentModel document = documentProvider.addDocument(null);

        // Send document
        return document;
    }

    public DocumentModel updateDocument(Long id, DocumentRepresentation representation) {
        DocumentModel document = documentProvider.getDocumentById(id).orElseThrow(NullPointerException::new);
        if (document.isReadOnly()) {
            throw new ModelReadOnlyException("Document is read only");
        }
        Document xml = xmlDocumentProvider.buildDocument(representation);
        processXmlDocument(xml);
        return document;
    }

}
