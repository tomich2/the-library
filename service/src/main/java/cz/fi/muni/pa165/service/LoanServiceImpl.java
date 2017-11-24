package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.library.persistance.dao.LoanDao;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import java.util.List;
import javax.inject.Inject;

import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import org.springframework.stereotype.Service;
/**
 * @author Jan Tlamicha(xtlamich)
 */
@Service
public class LoanServiceImpl implements LoanService{
    @Inject
    private LoanDao loanDao;

    @Override
    public void create(Loan loan) throws DataAccessException {
        if(loan == null || loan.getMember() == null || loan.getLoanItems().isEmpty() || loan.getLoanCreated() == null){
            throw new DataAccessException("Loan is null");
        }
        loanDao.create(loan);
    }

    @Override
    public void delete(Loan loan) throws DataAccessException {
        if(loan == null || loan.getId() < 0){
            throw new DataAccessException("Loan is null");
        }
        loanDao.delete(loan);
    }

    @Override
    public Loan findById(Long id) throws DataAccessException {
        if(id < 0){
            throw new DataAccessException("Id lower than 0");
        }
        return loanDao.findById(id);
    }

    @Override
    public List<Loan> allLoansOfMember(Member member) throws DataAccessException {
        if(member == null){
            throw new DataAccessException("Member is null");
        }
        return loanDao.allLoansOfMember(member);
    }

    @Override
    public List<Loan> findAll() throws DataAccessException {
        return loanDao.findAll();
    }

    @Override
    public void update(Loan loan) throws DataAccessException {
        if(loan == null || findById(loan.getId()) == null){
            throw new DataAccessException("Loan is null");
        }
        loanDao.update(loan);
    }
    
    
    
    
}
