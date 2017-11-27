package cz.fi.muni.pa165.facade;


import cz.fi.muni.pa165.config.MappingService;
import cz.fi.muni.pa165.dto.LoanItemDTO;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.service.LoanItemService;
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
 * LoanItemFacade test.
 * @author Michael Cada
 */
@RunWith(MockitoJUnitRunner.class)
public class LoanItemFacadeTest {
    private static VerificationMode once = times(1);

    private LoanItemFacadeImpl loanItemFacade;

    @Mock
    private LoanItem loanItem;

    @Mock
    private LoanItemDTO loanItemDTO;

    @Mock
    private LoanItemService loanItemService;

    @Mock
    private MappingService mappingService;

    @Before
    public void setUp() {
        loanItemFacade = new LoanItemFacadeImpl(loanItemService, mappingService);
    }

    @Test
    public void createCallsServiceAndReturnsId() {
        Long expectedId = new Long(5);
        when(mappingService.map(loanItemDTO, LoanItem.class)).thenReturn(loanItem);
        when(loanItemService.create(loanItem)).thenReturn(expectedId);

        Long actualId = loanItemFacade.create(loanItemDTO);

        verify(loanItemService, once).create(loanItem);
        Assert.assertEquals(expectedId, actualId);
    }

    @Test
    public void updateCallsService() {
        when(mappingService.map(loanItemDTO, LoanItem.class)).thenReturn(loanItem);

        loanItemFacade.update(loanItemDTO);

        verify(loanItemService, once).update(loanItem);
    }

    @Test
    public void deleteCallsService() {
        when(mappingService.map(loanItemDTO, LoanItem.class)).thenReturn(loanItem);

        loanItemFacade.delete(loanItemDTO);

        verify(loanItemService, once).delete(loanItem);
    }

    @Test
    public void deleteByIdFindsAndDeletesLoanItem() {
        Long loanItemId = new Long(5);
        when(loanItemService.findById(loanItemId)).thenReturn(loanItem);

        loanItemFacade.delete(loanItemId);

        verify(loanItemService, once).delete(loanItem);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteByIdThrowsExceptionIfNotFound() {
        Long loanItemId = new Long(5);
        when(loanItemService.findById(loanItemId)).thenReturn(null);

        loanItemFacade.delete(loanItemId);
    }

    @Test
    public void findByIdCallsService() {
        Long loanItemId = new Long(5);
        when(loanItemService.findById(loanItemId)).thenReturn(loanItem);
        when(mappingService.map(loanItem, LoanItemDTO.class)).thenReturn(loanItemDTO);

        LoanItemDTO actualLoanItem = loanItemFacade.findById(loanItemId);

        verify(loanItemService, once).findById(loanItemId);
        Assert.assertEquals(loanItemDTO, actualLoanItem);
    }

    @Test
    public void findAllCallsService() {
        List<LoanItem> loanItemList = Arrays.asList(loanItem);
        List<LoanItemDTO> loanItemDTOList = Arrays.asList(loanItemDTO);
        when(loanItemService.findAll()).thenReturn(loanItemList);
        when(mappingService.map(loanItemList, LoanItemDTO.class)).thenReturn(loanItemDTOList);

        List<LoanItemDTO> actualLoanItemDTOList = loanItemFacade.findAll();

        Assert.assertEquals(loanItemDTOList, actualLoanItemDTOList);
    }

}
