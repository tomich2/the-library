package cz.fi.muni.pa165.library.persistance.dao;

import cz.fi.muni.pa165.library.persistance.dao.base.CrudDao;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;

import java.util.List;

/**
 *
 * Database access object (DAO) for entity class LoanItem.
 * @author mcada
 */
public interface LoanItemDao extends CrudDao<LoanItem> {

    /**
     * Persists new LoanItem into persistence context
     *
     * @param loanItem item to be created
     */
    void create(LoanItem loanItem) throws DataAccessException;

    /**
     * Removes LoanItem from persistence context
     *
     * @param loanItem item to be deleted
     */
    void delete(LoanItem loanItem) throws DataAccessException;

    /**
     * Updates persisted LoanItem
     *
     * @param loanItem item to be updated
     */
    void update(LoanItem loanItem) throws DataAccessException;

    /**
     * Finds LoanItem by his id
     *
     * @param id the specified id
     * @return LoanItem with given id or null if not found
     */
    LoanItem findById(Long id) throws DataAccessException;

    /**
     * Finds all LoanItems which belongs to given loan
     *
     * @param loan the given loan
     * @return list of LoanItems
     */
    List<LoanItem> findByLoan(Loan loan) throws DataAccessException;

    /**
     * Finds all LoanItems from the database
     *
     * @return list of all LoanItems
     */
    List<LoanItem> findAll() throws DataAccessException;
}