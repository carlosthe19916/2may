package may.config.files;

/**
 * This class will persist and upload (if possible) files to a storage server.
 */
public interface FileProvider {

    /**
     * Search a file by its id
     *
     * @return File
     */
    FileModel getFileById(String id);

    /**
     * Persist the file.
     *
     * @param file to be persisted.
     */
    FileModel addFile(java.io.File file) throws FileException;

    /**
     * Persist the file
     *
     * @param filename of file.
     * @param bytes    file expressed in bytes.
     */
    FileModel addFile(String filename, byte[] bytes) throws FileException;

    /**
     * @param id of file
     */
    boolean removeFile(String id);

    /**
     * @param file to be removed
     */
    boolean removeFile(FileModel file);

}
