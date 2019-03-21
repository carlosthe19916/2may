package may.config.models.exceptions;

public class ModelReadOnlyException extends RuntimeException {

    public ModelReadOnlyException() {
        super();
    }

    public ModelReadOnlyException(String message) {
        super(message);
    }

}
