package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.Member;

import java.util.List;

/**
 * Service layer for loan entity.
 *
 * @author Jan Tlamicha(xtlamich)
 */
public interface LoanService {
     /**
     * Create loan in database
     *
     * @param loan to be created
     */
     void create(Loan loan) throws IllegalArgumentException;

    /**
     * Delete loan from database
     *
     * @param loan to be deleted
     */
     void delete(Loan loan) throws IllegalArgumentException;

    /**
     * Find a loan with specific id from the database
     *
     * @param id of loan to be returned
     * @return loan with specified Id 
     */
     Loan findById(Long id) throws IllegalArgumentException;

    /**
     * Returns all loans for the specific member 
     *
     * @param member for witch loans should be returned
     * @return all loans for specified member 
     */
    
    List<Loan> allLoansOfMember(Member member) throws IllegalArgumentException;

    /**
     * Returns all  loans of the member
     *
     * @return all loans 
     */
    List<Loan> findAll();

    /**
     * Updates specified loan
     *
     * @param loan to be updated
     */
    void update(Loan loan) throws IllegalArgumentException;

}
