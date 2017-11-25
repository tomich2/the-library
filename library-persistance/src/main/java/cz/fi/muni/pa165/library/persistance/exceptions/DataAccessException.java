package cz.fi.muni.pa165.library.persistance.exceptions;

/**
 * Exception for data access problems.
 */
public class DataAccessException extends Exception {

    public DataAccessException(String msg){
        super(msg);
    }
    public DataAccessException(){
        super();
    }
}
