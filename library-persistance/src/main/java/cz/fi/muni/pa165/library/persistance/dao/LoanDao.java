package cz.fi.muni.pa165.library.persistance.dao;

import cz.fi.muni.pa165.library.persistance.dao.base.CrudDao;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;

import java.util.List;

/**
 * Database access object (DAO) for entity class Loan.
 * @author Jan Tlamicha (xtlamich)
 */
public interface LoanDao extends CrudDao<Loan> {
    
    /**
     * Create loan in database
     *
     * @param loan to be created
     */
    void create(Loan loan) throws DataAccessException;

    /**
     * Delete loan from database
     *
     * @param loan to be deleted
     */
    void delete(Loan loan) throws DataAccessException;

    /**
     * Find a loan with specific id from the database
     *
     * @param id of loan to be returned
     * @return loan with specified Id 
     */
    Loan findById(Long id) throws DataAccessException;

    /**
     * Returns all loans for the specific member 
     *
     * @param member for witch loans should be returned
     * @return all loans for specified member 
     */

    List<Loan> allLoansOfMember(Member member) throws DataAccessException;

    /**
     * Returns all  loans of the member
     *
     * @return all loans 
     */
    List<Loan> findAll() throws DataAccessException;

    /**
     * Updates specified loan
     *
     * @param loan to be updated
     */
    void update(Loan loan) throws DataAccessException;
}
