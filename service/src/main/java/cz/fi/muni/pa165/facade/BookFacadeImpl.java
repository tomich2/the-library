package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.MappingService;
import cz.fi.muni.pa165.dto.BookDTO;
import cz.fi.muni.pa165.facade.base.CrudFacadeImpl;
import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.service.BookService;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Palenik
 */
@Service
public class BookFacadeImpl extends CrudFacadeImpl<BookDTO, Book> implements BookFacade {

    @Inject
    public BookFacadeImpl(BookService bookService, MappingService mappingService) {
        super(bookService, mappingService, BookDTO.class, Book.class);
    }
}