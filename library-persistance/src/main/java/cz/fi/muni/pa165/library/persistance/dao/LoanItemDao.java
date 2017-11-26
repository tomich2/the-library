package cz.fi.muni.pa165.library.persistance.dao;

import cz.fi.muni.pa165.library.persistance.dao.base.CrudDao;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;

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
    public void create(LoanItem loanItem);

    /**
     * Removes LoanItem from persistence context
     *
     * @param loanItem item to be deleted
     */
    public void delete(LoanItem loanItem);

    /**
     * Updates persisted LoanItem
     *
     * @param loanItem item to be updated
     */
    public void update(LoanItem loanItem);

    /**
     * Finds LoanItem by his id
     *
     * @param id the specified id
     * @return LoanItem with given id or null if not found
     */
    public LoanItem findById(Long id);

    /**
     * Finds all LoanItems which belongs to given loan
     *
     * @param loan the given loan
     * @return list of LoanItems
     */
    public List<LoanItem> findByLoan(Loan loan);

    /**
     * Finds all LoanItems from the database
     *
     * @return list of all LoanItems
     */
    public List<LoanItem> findAll();
}