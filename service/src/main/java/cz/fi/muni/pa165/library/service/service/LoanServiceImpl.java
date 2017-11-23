package cz.fi.muni.pa165.library.service.service;

import cz.fi.muni.pa165.library.persistance.dao.LoanDao;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import java.util.List;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
/**
 * @author Jan Tlamicha(xtlamich)
 */
@Service
public class LoanServiceImpl implements LoanService{
    @Inject
    private LoanDao loanDao;
    
    @Inject
    private BookService bookService;

    @Override
    public void create(Loan loan) throws IllegalArgumentException {
        loanDao.create(loan);
    }

    @Override
    public void delete(Loan loan) throws IllegalArgumentException {
        if(loan == null){
            throw new IllegalArgumentException("Loan not found");
        }
        loanDao.delete(loan);
    }

    @Override
    public Loan findById(Long id) throws IllegalArgumentException {
        if(id < 0){
            throw new IllegalArgumentException("Id lower than 0");
        }
        return loanDao.findById(id);
    }

    @Override
    public List<Loan> allLoansOfMember(Member member) throws IllegalArgumentException {
        if(member == null){
            throw new IllegalArgumentException("Member is null");
        }
        return loanDao.allLoansOfMember(member);
    }

    @Override
    public List<Loan> findAll() {
        return loanDao.findAll();
    }

    @Override
    public void update(Loan loan) throws IllegalArgumentException {
        if(loan == null){
            throw new IllegalArgumentException("Loan is null");
        }
        loanDao.update(loan);
    }
    
    
    
    
}
