package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.exception.LibraryServiceException;
import cz.fi.muni.pa165.library.persistance.dao.MemberDao;
import cz.fi.muni.pa165.library.persistance.dao.base.CrudDao;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.service.base.CrudServiceImpl;   
import java.util.List;
import javax.inject.Named;

import javax.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Implementation for member service.
 *
 * @author Tomáš Chomo
 */
@Named
public class MemberServiceImpl extends CrudServiceImpl<Member> implements MemberService {

    @Inject
    MemberDao memberDao;
    
    @Inject
    private PasswordEncoder encoder;
      
    @Inject
    public MemberServiceImpl(CrudDao<Member> crudDao) {
        super(crudDao);
        this.memberDao = memberDao;
    }

    @Override
    public void registerMember(Member member, String password) {
        if (password.isEmpty()) {
            throw new LibraryServiceException("Password may not be empty");
        }
        member.setJoinedDate(new Date());
        member.setPasswordHash(encoder.encode(password));
        memberDao.create(member);

    }

    @Override
    public boolean isAdmin(Member member) {
        return member.isAdmin();
    }

    @Override
    public void makeAdmin(Member member) {
        if (member == null) {
            throw new LibraryServiceException("Member doesn't exist");
        }
        member.setIsAdmin(true);
        memberDao.update(member);
    }
    
}
