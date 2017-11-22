
package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.CreateMemberDTO;
import cz.fi.muni.pa165.dto.LoanDTO;
import cz.fi.muni.pa165.dto.MemberDTO;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.service.MemberService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tchomo
 */
@Service
@Transactional
public class MemberFacadeImpl implements MemberFacade{

   @Inject
   private ListMapper mapper;

    @Inject
    private MemberService service;
    
    @Override
    public MemberDTO findById(Long id) {
        return mapper.map(service.findById(id), MemberDTO.class);
    }

    @Override
    public List<MemberDTO> findAll() {
         return mapper.map(service.findAll(), MemberDTO.class);
    }

    @Override
    public void deleteMember(Long id) {
       service.delete(service.findById(id));
    }


    @Override
    public Long registerMember(CreateMemberDTO memberReg) {
        Member member = mapper.map(memberReg, Member.class);
        service.registerMember(member);
        return member.getId();  
    }

    @Override
    public void updateMember(Long id, CreateMemberDTO member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
