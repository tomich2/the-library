package cz.fi.muni.pa165.library.persistance.dao;

import cz.fi.muni.pa165.library.persistance.entity.Book;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void create(Book book) {
        em.persist(book);
    }

    @Override
    public void delete(Book book) {
        Objects.requireNonNull(book, "null argument book");
        em.remove(findById(book.getId()));
    }

    @Override
    public void update(Book book) {
       em.merge(book);
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class)
                                                        .getResultList();
    }

    @Override
    public Book findById(Long id) {
        return em.find(Book.class, id);
    }
    
}
