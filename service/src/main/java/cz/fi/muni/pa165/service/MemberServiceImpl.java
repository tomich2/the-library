package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.exception.LibraryServiceException;
import cz.fi.muni.pa165.library.persistance.dao.LoanDao;
import cz.fi.muni.pa165.library.persistance.dao.MemberDao;
import cz.fi.muni.pa165.library.persistance.dao.base.CrudDao;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.base.CrudServiceImpl;
import javax.inject.Named;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Implementation for member service.
 *
 * @author Tomáš Chomo
 */
@Named
public class MemberServiceImpl extends CrudServiceImpl<Member> implements MemberService {


    @Inject
    private LoanDao loanDao;

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
    public void registerMember(Member member, String password) throws DataAccessException {
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
    public void makeAdmin(Member member) throws DataAccessException {
        if (member == null) {
            throw new LibraryServiceException("Member doesn't exist");
        }
        member.setIsAdmin(true);
        memberDao.update(member);
    }

    /**
     * Active members are those who loaned at least one book
     * @throws DataAccessException
     */
    public Set<Member> getActiveMembers() throws DataAccessException {
        Set<Member> activeMembers = new HashSet<>();

        for(Loan loan : loanDao.findAll()) {
            activeMembers.add(loan.getMember());
        }

        return activeMembers;
    }
    
}
