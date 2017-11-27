package cz.fi.muni.pa165.facade;
import cz.fi.muni.pa165.dto.*;
import cz.fi.muni.pa165.facade.base.CrudFacade;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;


/**
 * Facade interface Member
 * 
 * @author tchomo
 */
public interface MemberFacade extends CrudFacade<MemberDTO> {
    
    /**
     * Makes authentication of member
     *
     * @param memberAuth DTO object containing authentication data
     * @return true if authorization passed, else false
     */
//    boolean authenticateMember(MemberAuthenticateDTO memberAuth);

    /**
     * Registers new member
     *
     * @param memberReg DTO object containing registration data
     * @return id of new member
     */
    Long registerMember(CreateMemberDTO memberReg) throws DataAccessException;
    
    /**
     * Checks if member has admin permissions
     *
     * @param id member id
     * @return boolean
     */
    boolean isAdmin(Long id);

    /**
     * Makes user admin
     * @param id of user
     */
    void makeAdmin(Long id) throws DataAccessException;
    
}
