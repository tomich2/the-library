
package cz.fi.muni.pa165.library.persistance.dao;

import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import org.springframework.stereotype.Repository;
/**
 * Implementation of DAO (data access object) for entity class Loan.
 * Entity manager exceptions rethrow DataAccessException.
 * @author Jan Tlamicha (xtlamich)
 */
@Repository
public class LoanDaoImpl implements LoanDao {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Loan loan) throws DataAccessException{
        try{
            em.persist(loan);
        } catch (Exception e){
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(Loan loan) throws DataAccessException{
        try{
            em.remove(findById(loan.getId()));
        } catch (Exception e){
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public Loan findById(Long id) throws DataAccessException {
        try {
            return em.find(Loan.class, id);
        } catch (Exception e){
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Loan> allLoansOfMember(Member member) throws DataAccessException {
        try{
            return em.createQuery("select l from Loan l where l.member = :member", Loan.class)
                                            .setParameter("member", member).getResultList();
        } catch (Exception e){
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Loan> findAll() throws DataAccessException {
        try {
            return em.createQuery("select l from Loan l", Loan.class).getResultList();
        } catch (Exception e){
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void update(Loan loan) throws DataAccessException {
        try {
            em.merge(loan);
        } catch (Exception e){
            throw new DataAccessException(e.getMessage());
        }
    }
    
}
