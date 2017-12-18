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
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Implementation for member service.
 *
 * @author Tomáš Chomo
 */
@Named
public class MemberServiceImpl extends CrudServiceImpl<Member> implements MemberService {


    private LoanDao loanDao;

    MemberDao memberDao;
    
    private PasswordEncoder encoder;
      
    @Inject
    public MemberServiceImpl(MemberDao memberDao, LoanDao loanDao) {
        super(memberDao);
        this.memberDao = memberDao;
        this.loanDao = loanDao;
        this.encoder = new BCryptPasswordEncoder();
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
        List<Loan> loanList = loanDao.findAll();

        //if not empty find active members otherwise return empty set
        if(!loanList.isEmpty()) {
            for(Loan loan : loanList) {
                activeMembers.add(loan.getMember());
            }
        }
        return activeMembers;
    }
    
    @Override
    public Member findByEmail(String email) {
        List<Member> result = memberDao.findByEmail(email);
        if (!result.isEmpty()) {
            return result.get(0);
        } else {
            return null;
        }
    }
    
    @Override
    public boolean authenticateMember(Member member, String unencryptedPassword) {
        if (member == null) {
            return false;
        }
        return member.getPasswordHash().equals(encoder.encode(unencryptedPassword));
    }
    
}
