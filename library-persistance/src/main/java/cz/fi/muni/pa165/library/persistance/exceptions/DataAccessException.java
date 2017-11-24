package cz.fi.muni.pa165.library.persistance.exceptions;

public class DataAccessException extends org.springframework.dao.DataAccessException {

    public DataAccessException(String msg){
        super(msg);
    }
}
