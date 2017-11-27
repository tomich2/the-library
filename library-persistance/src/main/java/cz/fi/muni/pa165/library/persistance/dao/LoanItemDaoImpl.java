package cz.fi.muni.pa165.library.persistance.dao;

import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 * Implementation of DAO (data access object) for entity class LoanItem.
 * @author mcada
 */
@Repository
public class LoanItemDaoImpl implements LoanItemDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(LoanItem loanItem) throws DataAccessException {
        try{
            em.persist(loanItem);
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

    @Override
    public void delete(LoanItem loanItem) throws DataAccessException {
        try{
            Objects.requireNonNull(loanItem, "null argument loanItem");
            em.remove(findById(loanItem.getId()));
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }



    @Override
    public void update(LoanItem loanItem) throws DataAccessException {
        try{
            em.merge(loanItem);
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

    @Override
    public LoanItem findById(Long id) throws DataAccessException {
        try{
            return em.find(LoanItem.class, id);
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<LoanItem> findByLoan(Loan loan) throws DataAccessException {
        try{
        return em.createQuery("SELECT l FROM LoanItem l WHERE l.loan = :loanid", LoanItem.class)
                .setParameter("loanid", loan)
                .getResultList();
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<LoanItem> findAll() throws DataAccessException {
        try{
            return em.createQuery("select l from LoanItem l", LoanItem.class).getResultList();
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

}