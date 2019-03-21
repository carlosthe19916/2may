package may.config.files;

public class FileException extends RuntimeException {

    public FileException(String message) {
        super(message);
    }

    public FileException(Throwable e) {
        super(e);
    }

}
