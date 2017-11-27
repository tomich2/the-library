package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.MappingService;
import cz.fi.muni.pa165.dto.MemberDTO;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.service.MemberService;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Testing of Member facade
 * 
 * @author Tomáš Chomo tchomo
 */
@RunWith(MockitoJUnitRunner.class)
public class MemberFacadeTest {
    private static VerificationMode once = times(1);

    private MemberFacadeImpl memberFacade;
   
    @Mock
    private Member member;

    @Mock
    private MemberDTO memberDTO;
    

    @Mock
    private MemberService memberService;
   
    @Mock
    private MappingService mappingService; 
    
    
    @Before
    public void setUp() {
        memberFacade = new MemberFacadeImpl(memberService, mappingService);
    }
    
    @Test
    public void createCallsServiceAndReturnsId() {
        Long expectedId = new Long(5);
        when(mappingService.map(memberDTO, Member.class)).thenReturn(member);
        when(memberService.create(member)).thenReturn(expectedId);

        Long actualId = memberFacade.create(memberDTO);

        verify(memberService, once).create(member);
        Assert.assertEquals(expectedId, actualId);
    }
    
    
    @Test
    public void updateCallsService() {
        when(mappingService.map(memberDTO, Member.class)).thenReturn(member);

        memberFacade.update(memberDTO);

        verify(memberService, once).update(member);
    }
    
    @Test
    public void deleteCallsService() {
        when(mappingService.map(memberDTO, Member.class)).thenReturn(member);

        memberFacade.delete(memberDTO);

        verify(memberService, once).delete(member);
    }
    
    @Test
    public void deleteByIdFindsAndDeletesMember() {
        Long memberId = new Long(5);
        when(memberService.findById(memberId)).thenReturn(member);

        memberFacade.delete(memberId);

        verify(memberService, once).delete(member);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deleteByIdThrowsExceptionIfNotFound() {
        Long memberId = new Long(5);
        when(memberService.findById(memberId)).thenReturn(null);

        memberFacade.delete(memberId);
    }
    
    @Test
    public void findByIdCallsService() {
        Long memberId = new Long(5);
        when(memberService.findById(memberId)).thenReturn(member);
        when(mappingService.map(member, MemberDTO.class)).thenReturn(memberDTO);

        MemberDTO actualMembertem = memberFacade.findById(memberId);

        verify(memberService, once).findById(memberId);
        Assert.assertEquals(memberDTO, actualMembertem);
    }

    @Test
    public void findAllCallsService() {
        List<Member> memberList = Arrays.asList(member);
        List<MemberDTO> memberDTOList = Arrays.asList(memberDTO);
        when(memberService.findAll()).thenReturn(memberList);
        when(mappingService.map(memberList, MemberDTO.class)).thenReturn(memberDTOList);

        List<MemberDTO> actualMemberDTOList = memberFacade.findAll();

        Assert.assertEquals(memberDTOList, actualMemberDTOList);
    }

}
