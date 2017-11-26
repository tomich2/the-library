
package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.MappingService;
import cz.fi.muni.pa165.dto.CreateMemberDTO;
import cz.fi.muni.pa165.dto.LoanDTO;
import cz.fi.muni.pa165.dto.MemberDTO;
import cz.fi.muni.pa165.facade.base.CrudFacadeImpl;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.service.MemberService;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import cz.fi.muni.pa165.service.base.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tchomo
 */
@Service
@Transactional
public class MemberFacadeImpl extends CrudFacadeImpl<MemberDTO, Member> implements MemberFacade{
    
    @Inject
    private ListMapper mapper;
    
    @Inject
    private MemberService service; 
    
    @Inject
    public MemberFacadeImpl(CrudService<Member> crudService, MappingService mappingService) {
        super(crudService, mappingService, MemberDTO.class, Member.class);
    }
    

    @Override
    public boolean isAdmin(Long id) {
         return service.findById(id).isAdmin();
    }

    @Override
    public void makeAdmin(Long id) {
        service.makeAdmin(service.findById(id));
    }

    @Override
    public Long registerMember(CreateMemberDTO memberReg) {
        Member member = mapper.map(memberReg, Member.class);
        service.registerMember(member, memberReg.getPassword());
        return member.getId();
    }
}
