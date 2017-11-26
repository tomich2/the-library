package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.exception.LibraryServiceException;
import cz.fi.muni.pa165.library.persistance.dao.LoanItemDao;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;

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

    @Test(expected = LibraryServiceException.class)
    public void createWrapsRuntimeExceptionToPersistenceExceptionTest() {
        Mockito.doThrow(RuntimeException.class).when(loanItemDao).create(loanItem);
        loanItemService.create(loanItem);
    }

    @Test
    public void updateTest() {
        loanItemService.update(loanItem);
        Mockito.verify(loanItemDao, ONCE).update(loanItem);
    }



    @Test(expected = LibraryServiceException.class)
    public void updateWrapsRuntimeExceptionToPersistenceExceptionTest() {
        Mockito.doThrow(RuntimeException.class).when(loanItemDao).update(loanItem);
        loanItemService.update(loanItem);
    }

    @Test
    public void deleteTest() {
        loanItemService.delete(loanItem);
        Mockito.verify(loanItemDao, ONCE).delete(loanItem);
    }

    @Test(expected = LibraryServiceException.class)
    public void deleteWrapsRuntimeExceptionToPersistenceExceptionTest() {
        Mockito.doThrow(RuntimeException.class).when(loanItemDao).delete(loanItem);
        loanItemService.delete(loanItem);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(loanItemService.findById(loanItem.getId())).thenReturn(loanItem);
        LoanItem foundLoanItem = loanItemService.findById(loanItem.getId());

        org.junit.Assert.assertEquals(loanItem, foundLoanItem);
        Mockito.verify(loanItemDao, ONCE).findById(loanItem.getId());
    }

    @Test(expected = LibraryServiceException.class)
    public void findByIdWrapsRuntimeExceptionToPersistenceExceptionTest() {
        Mockito.doThrow(RuntimeException.class).when(loanItemDao).findById(loanItem.getId());
        loanItemService.findById(loanItem.getId());
    }

    @Test
    public void findAllTest() {
        List<LoanItem> loanItemList = new ArrayList<LoanItem>();
        loanItemList.add(loanItem);
        Mockito.when(loanItemService.findAll()).thenReturn(loanItemList);
        List<LoanItem> foundLoanItemsList = loanItemService.findAll();

        org.junit.Assert.assertEquals(loanItemList, foundLoanItemsList);
        Mockito.verify(loanItemDao, ONCE).findAll();
    }

    @Test(expected = LibraryServiceException.class)
    public void findAllWrapsRuntimeExceptionToPersistenceExceptionTest() {
        Mockito.doThrow(RuntimeException.class).when(loanItemDao).findAll();
        loanItemService.findAll();
    }

















}
