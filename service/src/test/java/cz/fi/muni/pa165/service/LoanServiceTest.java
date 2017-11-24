package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.library.persistance.dao.LoanDao;
import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class LoanServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private LoanDao loanDao;

    @Mock
    private LoanItemService loanItemService;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private LoanService loanService = new LoanServiceImpl();

    private Member member1;
    private Book book1;
    private LoanItem loanItem1;
    private Loan loan1;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        member1 = new Member();
        member1.setAddress("address");
        member1.setEmail("aaa@google.com");
        member1.setFirstName("FirstName");
        member1.setSurname("Surname");
        member1.setJoinedDate(new Date());
        book1 = new Book();
        book1.setAuthor("Author");
        book1.setTitle("Title");
        loan1 = new Loan();
        loan1.setId(1L);
        loan1.setMember(member1);
        loan1.setLoanCreated(new Date());
        Set<LoanItem> loanItems = new HashSet<>();
        loanItem1 = new LoanItem();
        loanItem1.setBook(book1);
        loanItem1.setLoan(loan1);
        loanItems.add(loanItem1);
        loan1.setLoanItems(loanItems);
    }

    @Test
    public void testCreate(){
        loanService.create(loan1);
        Mockito.verify(loanDao).create(loan1);
        assert loan1.getId() >= 0;
    }

    /*
    @Test(expected = Exception.class)
    public void createNullLoan(){
        loanService.create(null);
    }

    @Test(expected = Exception.class)
    public void createNullMember(){
        loan1.setMember(null);
        loanService.create(loan1);
    }
    */


}
