package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.dto.LoanItemDTO;
import cz.fi.muni.pa165.facade.LoanItemFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/loanItems")
public class LoanItemsController {

    @Inject
    private LoanItemFacade loanItemsFacade;

    /**
     * Returns all LoanItems as JSON.
     *
     * @return List of LoanItemsDTOs as JSON
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<LoanItemDTO> getLoanItems() {
        return loanItemsFacade.findAll();
    }

}
