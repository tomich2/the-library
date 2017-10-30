package cz.fi.muni.pa165.library.persistance.dao;

import  cz.fi.muni.pa165.library.persistance.entity.Book;
import java.util.List;

/**
 *Interface for data access objects which provide access to Book entity
 * 
 * @author Martin Palenik
 */
public interface BookDao {
    
    /**
     * Create a book in the database
     *
     * @param book to be created
     */
    public void create(Book book);
    
    /**
     * Delete the book from the database
     *
     * @param book to be deleted
     */
    public void delete(Book book);
    
    /**
     * Update a book information in the database
     *
     * @param book to be created
     */
    public void update(Book book);
    
    /**
     * Find all book in the database
     *
     * @return List of Books
     */
    public List<Book> findAll();
    
    /**
     * Find a book with specific id from the database
     *
     * @param id of the book
     * @return book with the given id
     */
    public Book findById(Long id);
    
}
