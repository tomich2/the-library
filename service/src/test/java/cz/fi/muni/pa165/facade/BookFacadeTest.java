package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.MappingService;
import cz.fi.muni.pa165.dto.BookDTO;
import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * BookFacade test.
 * @author Martin Palenik
 */
@RunWith(MockitoJUnitRunner.class)
public class BookFacadeTest {
    private static VerificationMode once = times(1);

    private BookFacadeImpl bookFacade;

    @Mock
    private Book book;

    @Mock
    private BookDTO bookDTO;

    @Mock
    private BookService bookService;

    @Mock
    private MappingService mappingService;

    @Before
    public void setUp() {
        bookFacade = new BookFacadeImpl(bookService, mappingService);
    }

    @Test
    public void createCallsServiceAndReturnsId() {
        Long expectedId = new Long(5);
        when(mappingService.map(bookDTO, Book.class)).thenReturn(book);
        when(bookService.create(book)).thenReturn(expectedId);

        Long actualId = bookFacade.create(bookDTO);

        verify(bookService, once).create(book);
        Assert.assertEquals(expectedId, actualId);
    }

    @Test
    public void updateCallsService() {
        when(mappingService.map(bookDTO, Book.class)).thenReturn(book);

        bookFacade.update(bookDTO);

        verify(bookService, once).update(book);
    }

    @Test
    public void deleteCallsService() {
        when(mappingService.map(bookDTO, Book.class)).thenReturn(book);

        bookFacade.delete(bookDTO);

        verify(bookService, once).delete(book);
    }

    @Test
    public void deleteByIdFindsAndDeletesBook() {
        Long bookId = new Long(5);
        when(bookService.findById(bookId)).thenReturn(book);

        bookFacade.delete(bookId);

        verify(bookService, once).delete(book);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteByIdThrowsExceptionIfNotFound() {
        Long bookId = new Long(5);
        when(bookService.findById(bookId)).thenReturn(null);

        bookFacade.delete(bookId);
    }

    @Test
    public void findByIdCallsService() {
        Long bookId = new Long(5);
        when(bookService.findById(bookId)).thenReturn(book);
        when(mappingService.map(book, BookDTO.class)).thenReturn(bookDTO);

        BookDTO actualBook = bookFacade.findById(bookId);

        verify(bookService, once).findById(bookId);
        Assert.assertEquals(bookDTO, actualBook);
    }

    @Test
    public void findAllCallsService() {
        List<Book> bookList = Arrays.asList(book);
        List<BookDTO> bookDTOList = Arrays.asList(bookDTO);
        when(bookService.findAll()).thenReturn(bookList);
        when(mappingService.map(bookList, BookDTO.class)).thenReturn(bookDTOList);

        List<BookDTO> actualBookDTOList = bookFacade.findAll();

        Assert.assertEquals(bookDTOList, actualBookDTOList);
    }
}