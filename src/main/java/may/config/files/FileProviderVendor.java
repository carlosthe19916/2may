package may.config.files;

import javax.inject.Qualifier;
import java.lang.annotation.*;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Documented
public @interface FileProviderVendor {

    Type type();

    enum Type {
        FILESYSTEM, JPA;
    }

}