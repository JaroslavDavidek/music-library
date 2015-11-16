package cz.fi.muni.pa165.exception;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author JaroslavDavidek
 */
public class InvalidParamDataAccessExpection extends DataAccessException{

    public InvalidParamDataAccessExpection(String msg) {
        super(msg);
    }
    
}
