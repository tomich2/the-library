
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
    public MemberFacadeImpl(CrudService<Member> crudService, MappingService mappingService) {
        super(crudService, mappingService, MemberDTO.class, Member.class);
    }
    
}
