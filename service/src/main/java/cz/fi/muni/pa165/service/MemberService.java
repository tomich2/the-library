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
}