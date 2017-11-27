package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.exception.LibraryServiceException;
import cz.fi.muni.pa165.library.persistance.dao.BookDao;
import cz.fi.muni.pa165.library.persistance.dao.LoanDao;
import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

/**
 * BookService test.
 * @author Martin Palenik
 */
@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    private static final VerificationMode ONCE = times(1);

    @Mock
    private BookDao bookDao;

    @Mock
    private LoanDao loanDao;

    private BookService bookService;

    private final Book book;

    public BookServiceTest() {
        this.book = new Book();
    }

    @Before
    public void setUp() {
        bookService = new BookServiceImpl(bookDao, loanDao);
        book.setId(new Long("1"));
    }

    @Test
    public void createTest() throws DataAccessException {
        Long bookId = bookService.create(book);
        Mockito.verify(bookDao, ONCE).create(book);
        Assert.assertEquals(book.getId(), bookId);
    }

    @Test(expected = LibraryServiceException.class)
    public void createWrapsRuntimeExceptionToPersistenceExceptionTest() throws DataAccessException {
        Mockito.doThrow(RuntimeException.class).when(bookDao).create(book);
        bookService.create(book);
    }

    @Test
    public void updateTest() throws DataAccessException {
        bookService.update(book);
        Mockito.verify(bookDao, ONCE).update(book);
    }

    @Test(expected = LibraryServiceException.class)
    public void updateWrapsRuntimeExceptionToPersistenceExceptionTest() throws DataAccessException {
        Mockito.doThrow(RuntimeException.class).when(bookDao).update(book);
        bookService.update(book);
    }

    @Test
    public void deleteTest() throws DataAccessException {
        bookService.delete(book);
        Mockito.verify(bookDao, ONCE).delete(book);
    }

    @Test(expected = LibraryServiceException.class)
    public void deleteWrapsRuntimeExceptionToPersistenceExceptionTest() throws DataAccessException {
        Mockito.doThrow(RuntimeException.class).when(bookDao).delete(book);
        bookService.delete(book);
    }

    @Test
    public void findByIdTest() throws DataAccessException {
        Mockito.when(bookService.findById(book.getId())).thenReturn(book);
        Book foundBook = bookService.findById(book.getId());

        org.junit.Assert.assertEquals(book, foundBook);
        Mockito.verify(bookDao, ONCE).findById(book.getId());
    }

    @Test(expected = LibraryServiceException.class)
    public void findByIdWrapsRuntimeExceptionToPersistenceExceptionTest() throws DataAccessException {
        Mockito.doThrow(RuntimeException.class).when(bookDao).findById(book.getId());
        bookService.findById(book.getId());
    }

    @Test
    public void findAllTest() throws DataAccessException {
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        Mockito.when(bookService.findAll()).thenReturn(bookList);
        List<Book> foundBooksList = bookService.findAll();

        org.junit.Assert.assertEquals(bookList, foundBooksList);
        Mockito.verify(bookDao, ONCE).findAll();
    }

    @Test(expected = LibraryServiceException.class)
    public void findAllWrapsRuntimeExceptionToPersistenceExceptionTest() throws DataAccessException {
        Mockito.doThrow(RuntimeException.class).when(bookDao).findAll();
        bookService.findAll();
    }
    
    @Test(expected = LibraryServiceException.class)
    public void getAllBooksLoanedAfterDateNull() throws DataAccessException {
        bookService.getAllBooksLoanedAfterDate(null);
    }
}