package cz.fi.muni.pa165.library.persistance.dao;

import cz.fi.muni.pa165.library.persistance.dao.base.CrudDao;
import  cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;

import java.util.List;

/**
 *Interface for data access objects which provide access to Book entity
 * 
 * @author Martin Palenik
 */
public interface BookDao extends CrudDao<Book> {
    
    /**
     * Create a book in the database
     *
     * @param book to be created
     */
    void create(Book book) throws DataAccessException;
    
    /**
     * Delete the book from the database
     *
     * @param book to be deleted
     */
    void delete(Book book) throws DataAccessException;
    
    /**
     * Update a book information in the database
     *
     * @param book to be created
     */
    void update(Book book) throws DataAccessException;
    
    /**
     * Find all book in the database
     *
     * @return List of Books
     */
    List<Book> findAll() throws DataAccessException;
    
    /**
     * Find a book with specific id from the database
     *
     * @param id of the book
     * @return book with the given id
     */
    Book findById(Long id) throws DataAccessException;
    
}
