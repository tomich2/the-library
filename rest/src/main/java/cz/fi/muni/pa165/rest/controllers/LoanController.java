package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.dto.LoanDTO;
import cz.fi.muni.pa165.facade.LoanFacade;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Rest controller for Loans.
 * @author xtlamich
 */

@RestController
@RequestMapping("/loans")
public class LoanController {

    final static Logger logger = LoggerFactory.getLogger(LoanController.class);

    @Inject
    private LoanFacade loanFacade;

    /**
     * get all the categories
     * @return list of CategoryDTOs
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<LoanDTO> getLoans() throws DataAccessException {

        logger.debug("rest getLoans()");
        return loanFacade.findAll();
    }

    /**
     *
     * Get one category specified by id
     *
     * @param id identifier for the category
     * @return CategoryDTO
     * @throws Exception ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final LoanDTO getLoan(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getLoan({})", id);

        LoanDTO loanDTO = loanFacade.findById(id);
        if (loanDTO == null) {
            throw new Exception("Loan not found");
        }

        return loanDTO;
    }
}
