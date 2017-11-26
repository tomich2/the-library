package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.CreateLoanDTO;
import cz.fi.muni.pa165.dto.LoanDTO;
import cz.fi.muni.pa165.facade.base.CrudFacade;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;

import java.util.List;

/**
 * Facade for Loan entity.
 *
 * @author Jan Tlamicha
 */
public interface LoanFacade extends CrudFacade<LoanDTO> {

    /**
     * Return all loans of member.
     * @param memberId Member ID.
     * @return List of all loans belonging to the member.
     */
    List<LoanDTO> allLoansOfMember(Long memberId) throws DataAccessException;

}
