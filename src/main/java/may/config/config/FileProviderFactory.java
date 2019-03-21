package may.config.config;

import may.config.files.FileProvider;
import may.config.files.FileProviderVendor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Optional;

public class FileProviderFactory {

    @Produces
    @ApplicationScoped
    public FileProvider provideSecurityContext(
            @ConfigProperty(name = Constants.FILE_PROVIDER_NAME) Optional<String> fileProvider,
            @FileProviderVendor(type = FileProviderVendor.Type.JPA) FileProvider jpa,
            @FileProviderVendor(type = FileProviderVendor.Type.FILESYSTEM) FileProvider filesystem
    ) {
        return null;
    }

    public static class UnknownFileProviderTypeException extends RuntimeException {
        UnknownFileProviderTypeException(FileProviderVendor.Type type) {
            super("Unknown file provider type:" + type);
        }
    }

}
