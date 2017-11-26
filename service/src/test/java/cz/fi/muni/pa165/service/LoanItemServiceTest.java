package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.library.persistance.dao.LoanItemDao;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.service.LoanItemService;
import cz.fi.muni.pa165.service.LoanItemServiceImpl;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.times;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class LoanItemServiceTest {

    private static final VerificationMode ONCE = times(1);


    @Mock
    private LoanItemDao loanItemDao;

    private LoanItemService loanItemService;

    private LoanItem loanItem =  new LoanItem();

    @Before
    public void setUp() {
        loanItemService = new LoanItemServiceImpl(loanItemDao);
        loanItem.setId(new Long("1"));
    }

    @Test
    public void createTest() {
        Long loanItemId = loanItemService.create(loanItem);
        Mockito.verify(loanItemDao, ONCE).create(loanItem);
        Assert.assertEquals(loanItem.getId(), loanItemId);
    }

















}
