package may.config.files.filesystem;

import may.config.files.FileModel;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileAdapter implements FileModel {

    private FileAttachEntity entity;
    private File file;

    public FileAdapter(FileAttachEntity entity, File file) {
        this.entity = entity;
        this.file = file;
    }

    @Override
    public String getId() {
        return entity.getId();
    }

    @Override
    public String getFilename() {
        return entity.getFileName();
    }

    @Override
    public byte[] getFileAsBytes() {
        InputStream is;
        try {
            is = new FileInputStream(file);
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public InputStream getFileStream() {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

}
