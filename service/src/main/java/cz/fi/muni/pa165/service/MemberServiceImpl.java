
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.library.persistance.dao.MemberDao;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import java.util.List;

import javax.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Inject
    MemberDao memberDao;
 

    @Override
    public Member findById(Long id) {
        return memberDao.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return memberDao.findAll();
    }

    @Override
    public void update(Member member) {
         memberDao.update(member);
    }

    @Override
    public void delete(Member member) {
        memberDao.delete(member);
    }

    @Override
    public void registerMember(Member member) {
        member.setJoinedDate(new Date());  
        memberDao.create(member);
    }
    
    
}
