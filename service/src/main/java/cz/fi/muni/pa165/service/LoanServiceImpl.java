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
        if(loan == null){
            throw new DataAccessException("Loan is null");
        }
        if(loanDao == null){
            throw new DataAccessException("LoanDao not working");
        }
        loanDao.create(loan);
    }

    @Override
    public void delete(Loan loan) throws DataAccessException {
        if(loan == null){
            throw new DataAccessException("Loan is null");
        }
        if(loanDao == null){
            throw new DataAccessException("LoanDao not working");
        }
        loanDao.delete(loan);
    }

    @Override
    public Loan findById(Long id) throws DataAccessException {
        if(id < 0){
            throw new DataAccessException("Id lower than 0");
        }
        if(loanDao == null){
            throw new DataAccessException("LoanDao not working");
        }
        return loanDao.findById(id);
    }

    @Override
    public List<Loan> allLoansOfMember(Member member) throws DataAccessException {
        if(member == null){
            throw new DataAccessException("Member is null");
        }
        if(loanDao == null){
            throw new DataAccessException("LoanDao not working");
        }
        return loanDao.allLoansOfMember(member);
    }

    @Override
    public List<Loan> findAll() {
        return loanDao.findAll();
    }

    @Override
    public void update(Loan loan) throws DataAccessException {
        if(loan == null){
            throw new DataAccessException("Loan is null");
        }
        if(loanDao == null){
            throw new DataAccessException("LoanDao not working");
        }
        loanDao.update(loan);
    }
    
    
    
    
}
