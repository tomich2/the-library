package cz.fi.muni.pa165.library.persistance.dao;

import cz.fi.muni.pa165.library.persistance.entity.Member;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import org.springframework.stereotype.Repository;
/**
 * Implementation of Member DAO interface with basic CRUD operations
 * 
 * @author Tomas Chomo (xchomo)
 */

@Repository
public class MemberDaoImpl implements MemberDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Member member) throws DataAccessException {
        try{
            em.persist(member);
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

    @Override
    public void delete(Member member) throws DataAccessException {
        try{
            Objects.requireNonNull(member, "null argument member");
             em.remove(findById(member.getId()));
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

    @Override
    public void update(Member member) throws DataAccessException {
        try{
        em.merge(member);
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Member> findAll() throws DataAccessException {
        try{
            return em.createQuery("select m from Member m", Member.class).getResultList();
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }

    @Override
    public Member findById(Long id) throws DataAccessException {
        try{
            return em.find(Member.class, id);
        } catch (Exception e){
            throw new DataAccessException(e);
        }
    }
    
    @Override
    public List<Member> findByEmail(String email) {
        return em.createQuery("from Member where email = :email", Member.class).setParameter("email", email).getResultList();
    }
    
}
