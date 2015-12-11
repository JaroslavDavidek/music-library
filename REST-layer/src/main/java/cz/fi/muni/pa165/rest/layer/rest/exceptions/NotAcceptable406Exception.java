package cz.fi.muni.pa165.rest.layer.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author JaroslavDavidek
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason="ERROR 406 - The given parameter is invalid.")
public class NotAcceptable406Exception extends RuntimeException {
    
}
