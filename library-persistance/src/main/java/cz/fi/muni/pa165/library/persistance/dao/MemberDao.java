package cz.fi.muni.pa165.library.persistance.dao;
import cz.fi.muni.pa165.library.persistance.dao.base.CrudDao;
import  cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;

import java.util.List;
/**
 *  Data access object (DAO) Interface for Member class
 * 
 * @author Tomas Chomo (xchomo)
 */
public interface MemberDao extends CrudDao<Member> {
 
    /**
     * Create a member in the database
     *
     * @param member to be created
     */
    void create(Member member) throws DataAccessException;
    
    /**
     * Delete the member from the database
     *
     * @param member to be deleted
     */
    void delete(Member member) throws DataAccessException;
    
    /**
     * Update a member information in the database
     *
     * @param member to be created
     */
    void update(Member member) throws DataAccessException;
    
    /**
     * Find all members in the database
     *
     * @return List of members
     */
    List<Member> findAll() throws DataAccessException;
    
    /**
     * Find a member with specific id from the database
     *
     * @param id of the member
     * @return member with the given id
     */
    Member findById(Long id) throws DataAccessException;
}
