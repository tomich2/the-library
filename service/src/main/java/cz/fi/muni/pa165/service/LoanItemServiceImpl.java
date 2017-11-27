package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.library.persistance.dao.LoanItemDao;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.service.base.CrudServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Implementation for loan item service.
 *
 * @author Michael Cada
 */
@Service
public class LoanItemServiceImpl extends CrudServiceImpl<LoanItem> implements LoanItemService {

    private LoanItemDao loanItemDao;

    @Inject
    public LoanItemServiceImpl(LoanItemDao loanItemDao) {
        super(loanItemDao);
        this.loanItemDao = loanItemDao;
    }

}