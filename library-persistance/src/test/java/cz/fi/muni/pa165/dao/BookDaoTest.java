package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.library.persistance.config.PersistenceApplicationContext;
import cz.fi.muni.pa165.library.persistance.dao.BookDao;
import cz.fi.muni.pa165.library.persistance.entity.Book;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Testing the Book entity.
 * @author Martin Palenik
 */
@ContextConfiguration(classes=PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BookDaoTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    private BookDao bookDao;
    
    @PersistenceContext
    private EntityManager em;
    /*
    @PersistenceUnit
    private EntityManagerFactory emf;
    */
    private Book b1;
    private Book b2;
    
    
    @BeforeMethod
    public void setUp() {
        b1 = new Book("author1", "title1");
        b2 = new Book("author2", "title2");
    }
    
    
    @Test(expectedExceptions = {DataAccessException.class})
    public void createNull() throws DataAccessException {
        bookDao.create(null);
    }
    
    
    @Test
    public void create() throws DataAccessException {
        bookDao.create(b1);
        
        //em = emf.createEntityManager();
        List<Book> books = em.createQuery("select b from Book b where b.id=:id", Book.class).setParameter("id", b1.getId()).getResultList();
        Assert.assertEquals(b1, books.get(0));
        /*
        em.remove(b1);
        books = em.createQuery("select b from Book b where b.id=:id", Book.class).setParameter("id", b1.getId()).getResultList();
        Assert.assertEquals(books.size(), 0);
        */
    }
    
    @Test
    public void createMultipleCopyOfSameBooks() throws DataAccessException {
        Book b1copy = new Book(b1.getAuthor(), b1.getTitle());
        bookDao.create(b1);
        bookDao.create(b1copy);
        //em = emf.createEntityManager();
        List<Book> books = em.createQuery("select b from Book b", Book.class).getResultList();
        Assert.assertEquals(b1, books.get(0));
        Assert.assertEquals(b1copy, books.get(1));
        /*
        em.remove(b1);
        books = em.createQuery("select b from Book b where b.id=:id", Book.class).setParameter("id", b1.getId()).getResultList();
        Assert.assertEquals(books.size(), 0);
        */
    }
    
    @Test
    public void delete() throws DataAccessException {

        List<Book> booksMock = new ArrayList<>();

        em.persist(b1);
        em.persist(b2);
        
        booksMock.add(b1);
        booksMock.add(b2);
        
        List<Book> books = em.createQuery("select b from Book b", Book.class).getResultList();
        Assert.assertEquals(books, booksMock);
        bookDao.delete(b1);
        bookDao.delete(b2);
        books = em.createQuery("select b from Book b", Book.class).getResultList();
        Assert.assertEquals(books.size(), 0);
    }
    
    @Test(expectedExceptions = {DataAccessException.class})
    public void deleteNotExisting() throws DataAccessException {
        bookDao.delete(b1);
    }
    
    @Test
    public void findById() throws DataAccessException {

        em.persist(b1);
        em.persist(b2);
        
        List<Book> books = em.createQuery("select b from Book b where b.id=:id", Book.class).setParameter("id", b1.getId()).getResultList();
        Book bookFound = bookDao.findById(b1.getId());
        Assert.assertEquals(bookFound, b1);
        
        books = em.createQuery("select b from Book b where b.id=:id", Book.class).setParameter("id", b2.getId()).getResultList();
        
        bookFound = bookDao.findById(b2.getId());
        Assert.assertEquals(bookFound, b2);
    }
    
    @Test
    public void update() throws DataAccessException {

        em.persist(b1);
        em.persist(b2);
        
        List<Book> books = em.createQuery("select b from Book b where b.id=:id", Book.class).setParameter("id", b1.getId()).getResultList();
        Assert.assertEquals(books.size(), 1);
        Assert.assertEquals(books.get(0), b1);
        
        b1.setAuthor("New author");
        b1.setTitle("New title");
        bookDao.update(b1);
        
        books = em.createQuery("select b from Book b where b.id=:id", Book.class).setParameter("id", b1.getId()).getResultList();
        Assert.assertEquals(books.size(), 1);
        Assert.assertEquals(books.get(0), b1);
        
    }

    @Test
    public void findAll() throws DataAccessException {
        List<Book> booksMock = new ArrayList<>();
        em.persist(b1);
        em.persist(b2);
        
        booksMock.add(b1);
        booksMock.add(b2);
        
        Assert.assertEquals(bookDao.findAll(), booksMock);
    }
}
