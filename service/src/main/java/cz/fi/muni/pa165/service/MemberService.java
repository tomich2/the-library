package cz.fi.muni.pa165.service;
import  cz.fi.muni.pa165.library.persistance.entity.Member;
import java.util.List;
import org.springframework.stereotype.Service;
/**
 *
 * @author tchomo
 */
@Service
public interface MemberService {

     /**
      * Returns member with given id or null
      *
      * @param id of requested member
      * @return member or null
      */
     Member findById(Long id);
 
     /**
      * Returns all persisted members
      *
      * @return list of all members
      */
     List<Member> findAll();
 
     /**
      * Updates member stored in database
      *
      * @param member entity to be updated
      */
     void update(Member member);
 
     /**
      * Removes member from database
      *
      * @param member entity to be removed
      */
     void delete(Member member);
/**
     * Registers member
     *
     * @param member to registerMember
     */
    void registerMember(Member member);

 
}