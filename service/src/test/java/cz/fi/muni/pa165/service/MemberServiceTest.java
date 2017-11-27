package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.exception.LibraryServiceException;
import cz.fi.muni.pa165.library.persistance.dao.LoanDao;
import cz.fi.muni.pa165.library.persistance.dao.MemberDao;
import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;

import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import org.testng.Assert;

import java.util.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Testing of Member service
 * 
 * @author Tomáš Chomo tchomo
 */

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {
    
 private static final VerificationMode ONCE = times(1);


    @Mock
    private MemberDao memberDao;

    @Mock
    private LoanDao loanDao;

    private MemberService memberService;

    private Member member;
    private Loan loan1;
    private LoanItem loanItem1;
    private Book book1;
    private Set<Member> members;


    @Before
    public void setUp() {
        memberService = new MemberServiceImpl(memberDao, loanDao);
        member = new Member();
        member.setId(new Long("1"));

        book1 = new Book();
        book1.setAuthor("Author");
        book1.setTitle("Title");

        loan1 = new Loan();
        loan1.setId(1L);
        loan1.setMember(member);
        loan1.setLoanCreated(new Date());

        Set<LoanItem> loanItems = new HashSet<>();
        loanItem1 = new LoanItem();
        loanItem1.setBook(book1);
        loanItem1.setLoan(loan1);
        loanItems.add(loanItem1);

        loan1.setLoanItems(loanItems);

        Set<Loan> loans = new HashSet<>();
        loans.add(loan1);
        member.setLoans(loans);

        members = new HashSet<>();
        members.add(member);

    }


    @Test
    public void createTest() throws DataAccessException {
        Long memberId = memberService.create(member);
        Mockito.verify(memberDao, ONCE).create(member);
        Assert.assertEquals(member.getId(), memberId);
    }   
    
    
    @Test(expected = LibraryServiceException.class)
    public void createWrapsRuntimeExceptionToPersistenceExceptionTest() throws DataAccessException {
        Mockito.doThrow(RuntimeException.class).when(memberDao).create(member);
        memberService.create(member);
    }
    
    @Test
    public void updateTest() throws DataAccessException {
        memberService.update(member);
        Mockito.verify(memberDao, ONCE).update(member);
    }
    
    @Test(expected = LibraryServiceException.class)
    public void updateWrapsRuntimeExceptionToPersistenceExceptionTest() throws DataAccessException {
        Mockito.doThrow(RuntimeException.class).when(memberDao).update(member);
        memberService.update(member);
    }

    @Test
    public void deleteTest() throws DataAccessException {
        memberService.delete(member);
        Mockito.verify(memberDao, ONCE).delete(member);
    }

    @Test(expected = LibraryServiceException.class)
    public void deleteWrapsRuntimeExceptionToPersistenceExceptionTest() throws DataAccessException {
        Mockito.doThrow(RuntimeException.class).when(memberDao).delete(member);
        memberService.delete(member);
    }

    @Test
    public void findByIdTest() throws DataAccessException {
        Mockito.when(memberService.findById(member.getId())).thenReturn(member);
        Member foundMemberItem = memberService.findById(member.getId());

        org.junit.Assert.assertEquals(member, foundMemberItem);
        Mockito.verify(memberDao, ONCE).findById(member.getId());
    }

    @Test(expected = LibraryServiceException.class)
    public void findByIdWrapsRuntimeExceptionToPersistenceExceptionTest() throws DataAccessException {
        Mockito.doThrow(RuntimeException.class).when(memberDao).findById(member.getId());
        memberService.findById(member.getId());
    }

    @Test
    public void activeMembersTest() throws DataAccessException {
        List<Loan> loans = new LinkedList<Loan>();
        loans.add(loan1);
        when(loanDao.findAll()).thenReturn(loans);
        Set<Member> memberSet = memberService.getActiveMembers();

        Mockito.verify(loanDao, ONCE).findAll();
        Assert.assertEquals(memberSet.size(), members.size());
    }

    @Test
    public void findAllTest() throws DataAccessException {
        List<Member> memberList = new ArrayList<Member>();
        memberList.add(member);
        Mockito.when(memberService.findAll()).thenReturn(memberList);
        List<Member> foundMemberList = memberService.findAll();

        org.junit.Assert.assertEquals(memberList, foundMemberList);
        Mockito.verify(memberDao, ONCE).findAll();
    }

    @Test(expected = LibraryServiceException.class)
    public void findAllWrapsRuntimeExceptionToPersistenceExceptionTest() throws DataAccessException {
        Mockito.doThrow(RuntimeException.class).when(memberDao).findAll();
        memberService.findAll();
    }
}
