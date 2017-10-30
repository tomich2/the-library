package cz.fi.muni.pa165.dao;
import cz.fi.muni.pa165.library.persistance.config.PersistenceApplicationContext;
import cz.fi.muni.pa165.library.persistance.dao.LoanItemDao;
import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 *
 * Unit tests for LoanItemDao for implementation of basic CRUD operations.
 * @author mcada
 */
@ContextConfiguration(classes=PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LoanItemDaoTest extends AbstractTestNGSpringContextTests{


    @Autowired
    private LoanItemDao loanItemDao;

    @PersistenceContext
    private EntityManager em;

    private Loan loanOne;
    private Loan loanTwo;

    private Book bookOne;
    private Book bookTwo;

    private LoanItem myLoanItemOne;
    private Member member;

    @BeforeMethod
    public void setUp() {
        loanOne = new Loan();
        loanTwo = new Loan();

        bookOne = new Book("author1", "title1");
        bookTwo = new Book("author2", "title2");



        member = new Member();
        member.setAddress("address");
        member.setEmail("daas@gmail.com");
        member.setFirstName("firstname");
        member.setSurname("surname");
        member.setPhone("+420700000000");
        member.setJoinedDate(new Date());


        myLoanItemOne = new LoanItem();

        myLoanItemOne.setBook(bookOne);
        myLoanItemOne.setLoan(loanOne);

        loanOne.addLoanItem(myLoanItemOne);
        loanTwo.addLoanItem(myLoanItemOne);

        Date loanDate = new Date();
        Set<LoanItem> loanItems = new HashSet<>();
        loanItems.add(myLoanItemOne);

        loanOne.setLoanCreated(loanDate);
        loanOne.setLoanItems(loanItems);
        loanOne.setMember(member);

        loanTwo.setLoanCreated(loanDate);
        loanTwo.setLoanItems(loanItems);
        loanTwo.setMember(member);

        em.persist(bookOne);
        em.persist(bookTwo);
        em.persist(member);
        em.persist(loanOne);
        em.persist(loanTwo);
    }

    @Test(expectedExceptions = {org.springframework.dao.InvalidDataAccessApiUsageException.class})
    public void createNull(){
        loanItemDao.create(null);
    }

    @Test
    public void create(){

        loanItemDao.create(myLoanItemOne);

        List<LoanItem> loanItems = em.createQuery("select li from LoanItem li where li.id=:id", LoanItem.class).setParameter("id", myLoanItemOne.getId()).getResultList();
        Assert.assertEquals(myLoanItemOne, loanItems.get(0));

    }

    @Test
    public void delete(){
        List<LoanItem> loanItemsMock = new ArrayList<>();

        em.persist(myLoanItemOne);

        loanItemsMock.add(myLoanItemOne);

        List<LoanItem> loanItems = em.createQuery("select b from LoanItem b", LoanItem.class).getResultList();
        Assert.assertEquals(loanItems, loanItemsMock);
        loanItemDao.delete(myLoanItemOne);
        loanItems = em.createQuery("select b FROM LoanItem b", LoanItem.class).getResultList();
        Assert.assertEquals(loanItems.size(), 0);
    }

    @Test(expectedExceptions = {org.springframework.dao.InvalidDataAccessApiUsageException.class})
    public void deleteNotExisting(){
        loanItemDao.delete(myLoanItemOne);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void deleteNull(){
        loanItemDao.delete(null);
    }

    @Test
    public void update(){

        em.persist(myLoanItemOne);

        List<LoanItem> loans = em.createQuery("select l from LoanItem l where l.id=:id", LoanItem.class)
                .setParameter("id", myLoanItemOne.getId()).getResultList();

        Assert.assertEquals(loans.size(), 1);
        Assert.assertEquals(loans.get(0).getBook(),bookOne);
        Assert.assertEquals(loans.get(0).getLoan(),loanOne);


        myLoanItemOne.setBook(bookTwo);
        myLoanItemOne.setLoan(loanTwo);
        loanItemDao.update(myLoanItemOne);

        loans = em.createQuery("select l from LoanItem l where l.id=:id", LoanItem.class)
                .setParameter("id", myLoanItemOne.getId()).getResultList();

        Assert.assertEquals(loans.size(), 1);
        Assert.assertEquals(loans.get(0), myLoanItemOne);
        Assert.assertEquals(loans.get(0).getBook(),bookTwo);
        Assert.assertEquals(loans.get(0).getLoan(),loanTwo);



    }

    @Test
    public void findAll(){
        List<LoanItem> loanItemsMock = new ArrayList<>();


        em.persist(myLoanItemOne);

        loanItemsMock.add(myLoanItemOne);

        Assert.assertEquals(loanItemDao.findAll(), loanItemsMock);
    }

    @Test
    public void findById(){
        em.persist(myLoanItemOne);

        List<LoanItem> loanItems = em.createQuery("select l from LoanItem l where l.id=:id", LoanItem.class).setParameter("id", myLoanItemOne.getId()).getResultList();
        Assert.assertEquals(loanItems.size(), 1);
        LoanItem loanFound = loanItemDao.findById(myLoanItemOne.getId());
        Assert.assertEquals(loanFound, myLoanItemOne);
    }

    @Test
    public void findByLoan(){
        em.persist(myLoanItemOne);
        LoanItem loanFound = loanItemDao.findById(myLoanItemOne.getId());
        Assert.assertEquals(loanFound, myLoanItemOne);
    }
}