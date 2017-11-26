package cz.fi.muni.pa165.service;
import  cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.service.base.CrudService;
import java.util.List;
import org.springframework.stereotype.Service;
/**
 *
 * @author Tomáš Chomo tchomo
 */
@Service
public interface MemberService extends CrudService<Member>{
    /**
     * Registers member
     *
     * @param member to registerMember
     * @param unhashedPassword password string
     */
    void registerMember(Member member, String unhashedPassword);

    /**
     * Checks if member is admin
     *
     * @param member to check
     * @return whether member is an admin
     */
    boolean isAdmin(Member member);

    /**
     * Makes user admin
     * @param member to make admin
     */
    void makeAdmin(Member member);
    
    
}