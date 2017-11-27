package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.library.persistance.dao.BookDao;
import cz.fi.muni.pa165.library.persistance.dao.LoanDao;
import cz.fi.muni.pa165.library.persistance.dao.base.CrudDao;
import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.base.CrudServiceImpl;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation for Book service.
 *
 * @author Martin Palenik
 */
@Service
public class BookServiceImpl extends CrudServiceImpl<Book> implements BookService {

    @Inject
    private LoanDao loanDao;
    
    @Inject
    public BookServiceImpl(CrudDao<Book> crudDao) {
        super(crudDao);
    }

    @Override
    public Set<Book> getAllBooksLoanedAfterDate(Date date) throws DataAccessException {
        Set<Book> loanedBooks = new HashSet<>();

        //maybe we can support this search via DAO and db query so it is faster
        for(Loan loan : loanDao.findAll()) {
            if(loan.getLoanCreated().after(date)) {
                for (LoanItem item : loan.getLoanItems()) {
                    loanedBooks.add(item.getBook());
                }
            }
        }
        return loanedBooks;
    }
}