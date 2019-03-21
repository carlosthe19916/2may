package may.config.files.filesystem;

import may.config.files.FileException;
import may.config.files.FileModel;
import may.config.files.FileProvider;
import may.config.files.FileProviderVendor;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Transactional(value = Transactional.TxType.REQUIRES_NEW)
@ApplicationScoped
@FileProviderVendor(type = FileProviderVendor.Type.FILESYSTEM)
public class FileSystemFileProvider implements FileProvider {

    private static final Logger logger = Logger.getLogger(FileSystemFileProvider.class);

    @PersistenceContext
    EntityManager em;

    private static final String baseFolder = "filesystem";

    private Path buildFileName(String fileName) {
        return Paths.get(baseFolder, fileName);
    }

    @Override
    public FileModel getFileById(String fileId) {
        FileAttachEntity entity = em.find(FileAttachEntity.class, fileId);
        if (entity == null) {
            return null;
        }

        Path path = buildFileName(entity.getFileLocation());
        return null;
    }

    @Override
    public FileModel addFile(File file) throws FileException {
        return null;
    }

    @Override
    public FileModel addFile(String fileName, byte[] bytes) throws FileException {
        String id = UUID.randomUUID().toString();
        Path path = buildFileName(id);

        if (Files.exists(path)) {
            throw new FileException("File already exists");
        }
        if (!Files.exists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
                Files.write(path, bytes);
            } catch (IOException e) {
                throw new FileException(e);
            }
        }

        FileAttachEntity entity = new FileAttachEntity();
        entity.setId(id);
        entity.setFileName(fileName);
        entity.setFileLocation(path.toString());
        em.persist(entity);

        return null;
    }

    @Override
    public boolean removeFile(String fileId) {
        Path path = Paths.get(baseFolder, fileId);
        try {
            Files.delete(path);
            return true;
        } catch (Throwable e) {
            logger.errorf("Could not delete the file %s", path.toString());
            return false;
        }
    }

    @Override
    public boolean removeFile(FileModel file) {
        return removeFile(file.getId());
    }

}
