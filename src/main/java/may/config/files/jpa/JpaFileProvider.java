package may.config.files.jpa;

import may.config.files.FileException;
import may.config.files.FileModel;
import may.config.files.FileProvider;
import may.config.files.FileProviderVendor;
import may.config.files.jpa.entities.FileEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;

@Transactional
@ApplicationScoped
@FileProviderVendor(type = FileProviderVendor.Type.JPA)
public class JpaFileProvider implements FileProvider {

    @PersistenceContext
    EntityManager em;

    @Override
    public FileModel getFileById(String id) {
        return FileEntity.findById(id);
    }

    @Override
    public FileModel addFile(File file) throws FileException {
        return null;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public FileModel addFile(String fileName, byte[] fileBytes) throws FileException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename(fileName);
        fileEntity.setFileBytes(fileBytes);
        FileEntity.persist(fileEntity);
        return null;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public boolean removeFile(String fileId) {
        FileEntity.findById(fileId).delete();
        return true;
    }

    @Override
    public boolean removeFile(FileModel file) {
        FileEntity.findById(file.getId()).delete();
        return true;
    }

}
