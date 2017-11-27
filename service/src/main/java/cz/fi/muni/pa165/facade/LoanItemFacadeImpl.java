package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.MappingService;
import cz.fi.muni.pa165.dto.LoanItemDTO;
import cz.fi.muni.pa165.facade.base.CrudFacadeImpl;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.service.LoanItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;


/**
 * Facade implementation for basic create, update, delete, retrieve operations.
 *
 * @author Michael Cada
 */
@Service
@Transactional
public class LoanItemFacadeImpl extends CrudFacadeImpl<LoanItemDTO, LoanItem> implements LoanItemFacade {

    @Inject
    public LoanItemFacadeImpl(LoanItemService loanItemService, MappingService mappingService) {
        super(loanItemService, mappingService, LoanItemDTO.class, LoanItem.class);
    }
}
