package may.config.indexer.jpa;

import may.config.indexer.DocumentIndexer;
import org.w3c.dom.Document;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JpaDocumentIndexer implements DocumentIndexer {

    @Override
    public void index(Document xmlDocument) {

    }
}
