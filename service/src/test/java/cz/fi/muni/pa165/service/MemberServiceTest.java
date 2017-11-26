package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.exception.LibraryServiceException;
import cz.fi.muni.pa165.library.persistance.dao.MemberDao;
import cz.fi.muni.pa165.library.persistance.entity.Member;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;

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

    private MemberService memberService;

    private Member member =  new Member();

    @Before
    public void setUp() {
        memberService = new MemberServiceImpl(memberDao);
        member.setId(new Long("1"));
    }    
   
    @Test
    public void createTest() {
        Long memberId = memberService.create(member);
        Mockito.verify(memberDao, ONCE).create(member);
        Assert.assertEquals(member.getId(), memberId);
    }   
    
    
    @Test(expected = LibraryServiceException.class)
    public void createWrapsRuntimeExceptionToPersistenceExceptionTest() {
        Mockito.doThrow(RuntimeException.class).when(memberDao).create(member);
        memberService.create(member);
    }
    
    @Test
    public void updateTest() {
        memberService.update(member);
        Mockito.verify(memberDao, ONCE).update(member);
    }
    
    @Test(expected = LibraryServiceException.class)
    public void updateWrapsRuntimeExceptionToPersistenceExceptionTest() {
        Mockito.doThrow(RuntimeException.class).when(memberDao).update(member);
        memberService.update(member);
    }

    @Test
    public void deleteTest() {
        memberService.delete(member);
        Mockito.verify(memberDao, ONCE).delete(member);
    }

    @Test(expected = LibraryServiceException.class)
    public void deleteWrapsRuntimeExceptionToPersistenceExceptionTest() {
        Mockito.doThrow(RuntimeException.class).when(memberDao).delete(member);
        memberService.delete(member);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(memberService.findById(member.getId())).thenReturn(member);
        Member foundMemberItem = memberService.findById(member.getId());

        org.junit.Assert.assertEquals(member, foundMemberItem);
        Mockito.verify(memberDao, ONCE).findById(member.getId());
    }

    @Test(expected = LibraryServiceException.class)
    public void findByIdWrapsRuntimeExceptionToPersistenceExceptionTest() {
        Mockito.doThrow(RuntimeException.class).when(memberDao).findById(member.getId());
        memberService.findById(member.getId());
    }

    @Test
    public void findAllTest() {
        List<Member> memberList = new ArrayList<Member>();
        memberList.add(member);
        Mockito.when(memberService.findAll()).thenReturn(memberList);
        List<Member> foundMemberList = memberService.findAll();

        org.junit.Assert.assertEquals(memberList, foundMemberList);
        Mockito.verify(memberDao, ONCE).findAll();
    }

    @Test(expected = LibraryServiceException.class)
    public void findAllWrapsRuntimeExceptionToPersistenceExceptionTest() {
        Mockito.doThrow(RuntimeException.class).when(memberDao).findAll();
        memberService.findAll();
    }
}
