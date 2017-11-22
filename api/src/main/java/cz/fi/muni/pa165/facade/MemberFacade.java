package cz.fi.muni.pa165.facade;
import cz.fi.muni.pa165.dto.*;
import java.util.List;



/**
 *
 * @author tchomo
 */
public interface MemberFacade {
 
    
    
    /**
     * Finds member by id
     *
     * @param id member id
     * @return member dto or null
     */
    MemberDTO findById(Long id);
   
    /**
     * Returns all members
     *
     * @return list of members
     */
    List<MemberDTO> findAll();

    

    /**
     * Removes member
     *
     * @param id member id
     */
    void deleteMember(Long id);
//
//    /**
//     * Returns all loans made by member
//     *
//     * @param id member id
//     * @return list of loans
//     */
//    List<LoanDTO> getAllLoans(Long id);


    /**
     * Registers new member
     *
     * @param memberReg DTO object containing registration data
     * @return id of new member
     */
    Long registerMember(CreateMemberDTO memberReg);

  
    void updateMember(Long id, CreateMemberDTO member);
}
