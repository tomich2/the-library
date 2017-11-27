package cz.fi.muni.pa165.exception;

/**
 * Library service exception
 */
public class LibraryServiceException extends RuntimeException {
    public LibraryServiceException(String msg){
        super(msg);
    }

    public LibraryServiceException(String msg, Throwable ex){
        super(msg, ex);
    }

    public LibraryServiceException(String msg, Exception ex){
        super(msg, ex);
    }

}
