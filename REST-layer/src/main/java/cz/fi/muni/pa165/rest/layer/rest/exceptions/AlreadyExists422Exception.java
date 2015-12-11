package cz.fi.muni.pa165.rest.layer.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author JaroslavDavidek
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason="The resource already exists.")
public class AlreadyExists422Exception extends RuntimeException {
    
}
