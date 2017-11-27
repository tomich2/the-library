package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.library.persistance.dao.BookDao;
import cz.fi.muni.pa165.library.persistance.dao.base.CrudDao;
import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.service.base.CrudServiceImpl;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 * Implementation for Book service.
 *
 * @author Martin Palenik
 */
@Service
public class BookServiceImpl extends CrudServiceImpl<Book> implements BookService {

    private BookDao bookDao;
    
    @Inject
    public BookServiceImpl(CrudDao<Book> crudDao) {
        super(crudDao);
    }
}