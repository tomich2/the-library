package cz.fi.muni.pa165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mcada
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested resource was not found.")
public class ResourceNotFoundException extends RuntimeException {
}
