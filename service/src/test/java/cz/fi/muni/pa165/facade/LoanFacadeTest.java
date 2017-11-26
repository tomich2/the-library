package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.MappingService;
import cz.fi.muni.pa165.dto.CreateLoanDTO;
import cz.fi.muni.pa165.dto.LoanDTO;
import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mockito.verification.VerificationMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.*;

import static org.mockito.Mockito.*;

/**
 * LoanFacade test.
 * Services mocked.
 * @author Jan Tlamicha(xtlamich)
 */

@RunWith(MockitoJUnitRunner.class)
public class LoanFacadeTest {

    private static VerificationMode ONCE = times(1);
    @Mock
    private MappingService mappingService;

    @Mock
    private LoanService loanServiceMock;

    @Mock
    private LoanItemService loanItemServiceMock;

    @Mock
    private MemberService memberServiceMock;

    private LoanFacade loanFacade;

    private Member member1;
    private Book book1;
    private LoanItem loanItem1;
    private Loan loan1;

    @Before
    public void setUp() throws DataAccessException {
        MockitoAnnotations.initMocks(this);
        loanFacade = new LoanFacadeImpl(loanServiceMock, memberServiceMock, loanItemServiceMock, mappingService);
        member1 = new Member();
        member1.setAddress("address");
        member1.setEmail("aaa@google.com");
        member1.setFirstName("FirstName");
        member1.setSurname("Surname");
        member1.setJoinedDate(new Date());
        book1 = new Book();
        book1.setAuthor("Author");
        book1.setTitle("Title");
        book1.setId(1L);
        loan1 = new Loan();
        loan1.setId(1L);
        loan1.setMember(member1);
        loan1.setLoanCreated(new Date());
        Set<LoanItem> loanItems = new HashSet<>();
        loanItem1 = new LoanItem();
        loanItem1.setBook(book1);
        loanItem1.setLoan(loan1);
        loanItem1.setId(1L);
        loanItems.add(loanItem1);
        loan1.setLoanItems(loanItems);
        Set<Loan> loans = new HashSet<>();
        loans.add(loan1);
        member1.setLoans(loans);

        when(mappingService.map(loanItem1, LoanItem.class)).thenReturn(loanItem1);
        when(loanServiceMock.findById(loan1.getId())).thenReturn(loan1);
        when(memberServiceMock.findById(member1.getId())).thenReturn(member1);
        List<Loan> listLoans = new ArrayList<>();
        listLoans.addAll(member1.getLoans());
        when(loanServiceMock.allLoansOfMember(member1)).thenReturn(listLoans);
        when(loanServiceMock.findAll()).thenReturn(listLoans);
        when(loanItemServiceMock.findById(loanItem1.getId())).thenReturn(loanItem1);
    }

    @Test
    public void testCreateLoan() throws DataAccessException {
        //Capture loan instance inside facadeCreate ... instance from mapper
        ArgumentCaptor<Loan> captor = ArgumentCaptor.forClass(Loan.class);
        CreateLoanDTO loanCreateDTO = new CreateLoanDTO();
        Set<Long> loanItems = new HashSet<>();
        loanItems.add(loanItem1.getId());
        loanCreateDTO.setLoanitemIds(loanItems);
        loanCreateDTO.setMemberId(member1.getId());
        when(mappingService.map(loanCreateDTO, Loan.class)).thenReturn(loan1);
        loanFacade.makeLoan(loanCreateDTO);
        verify(loanServiceMock, ONCE).create(captor.capture());
        Loan entity = captor.getValue();
        Assert.assertSame(member1, entity.getMember());
        Assert.assertEquals(loan1.getLoanItems(), entity.getLoanItems());
    }

    @Test
    public void testFindById() throws DataAccessException {
        when(loanServiceMock.findById(loan1.getId())).thenReturn(loan1);
        LoanDTO mockDTo = new LoanDTO();
        mockDTo.setId(loan1.getId());
        when(mappingService.map(loan1, LoanDTO.class)).thenReturn(mockDTo);
        LoanDTO dto = loanFacade.findById(loan1.getId());
        Assert.assertEquals(dto.getId(), loan1.getId());
    }

    @Test
    public void testFindAll() throws DataAccessException {
        List<Loan> loans = new ArrayList<>();
        loans.add(loan1);
        LoanDTO dto = new LoanDTO();
        dto.setId(loan1.getId());
        List<LoanDTO> mockdtos = new ArrayList<>();
        mockdtos.add(dto);
        when(mappingService.map(loans, LoanDTO.class)).thenReturn(mockdtos);
        when(loanServiceMock.findAll()).thenReturn(loans);
        List<LoanDTO> dtos = loanFacade.findAll();
        Assert.assertEquals(1, dtos.size());
        Assert.assertEquals(dtos.get(0).getId(), loan1.getId());
    }

    @Test
    public void testAllLoansOfMember() throws DataAccessException{
        List<Loan> mockloans = new ArrayList<>();
        mockloans.addAll(member1.getLoans());
        when(loanServiceMock.allLoansOfMember(member1)).thenReturn(mockloans);
        List<LoanDTO> dtos = new ArrayList<>();
        LoanDTO dto = new LoanDTO();
        dto.setId(loan1.getId());
        dtos.add(dto);
        when(mappingService.map(mockloans, LoanDTO.class)).thenReturn(dtos);
        List<LoanDTO> loans = loanFacade.allLoansOfMember(member1.getId());
        Assert.assertEquals(loan1.getId(),loans.get(0).getId());
    }

    @Test
    public void testDelete() throws DataAccessException {
        loanFacade.delete(loan1.getId());
        verify(loanServiceMock, ONCE).delete(loan1);
    }



}
