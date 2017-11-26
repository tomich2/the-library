package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;

public interface LoanItemService {
    LoanItem findById(Long loanItemId) throws DataAccessException;
}
