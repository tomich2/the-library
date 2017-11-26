package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.MappingService;
import cz.fi.muni.pa165.dto.LoanItemDTO;
import cz.fi.muni.pa165.facade.base.CrudFacadeImpl;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.service.LoanItemService;

import javax.inject.Inject;

public class LoanItemFacadeImpl extends CrudFacadeImpl<LoanItemDTO, LoanItem> implements LoanItemFacade {

    @Inject
    public LoanItemFacadeImpl(LoanItemService loanItemService, MappingService mappingService) {
        super(loanItemService, mappingService, LoanItemDTO.class, LoanItem.class);
    }
}
