package cz.fi.muni.pa165.rest.layer.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author JaroslavDavidek
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="ERROR 404 - The resource has not been found.")
public class NotFound404Exception extends RuntimeException {
}