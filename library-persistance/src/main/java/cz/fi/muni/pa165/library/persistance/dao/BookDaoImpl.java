package cz.fi.muni.pa165.library.persistance.dao;

import cz.fi.muni.pa165.library.persistance.entity.Book;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import org.springframework.stereotype.Repository;

/** 
* Implementing the DAO.
*	@author Martin Palenik
*/

@Repository
public class BookDaoImpl implements BookDao {

    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Book book) throws DataAccessException {
        try {
            em.persist(book);
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

    @Override
    public void delete(Book book) throws DataAccessException {
        try{
            Objects.requireNonNull(book, "null argument book");
            em.remove(findById(book.getId()));
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

    @Override
    public void update(Book book) throws DataAccessException {
       try{
            em.merge(book);
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Book> findAll() throws DataAccessException {
        try{
        return em.createQuery("select b from Book b", Book.class)
                                                        .getResultList();
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

    @Override
    public Book findById(Long id) throws DataAccessException {
        try {
            return em.find(Book.class, id);
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }
    
}
