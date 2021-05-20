package utilities.data;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// create a custom Annotation
@Retention(RUNTIME)
@Target({ METHOD })
public @interface InjectData {
    /**
     * return the json path od the data file
     * @return
     */
    String json();
}

