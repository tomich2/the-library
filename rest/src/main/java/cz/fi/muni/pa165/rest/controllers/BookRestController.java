package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.dto.BookDTO;
import cz.fi.muni.pa165.facade.BookFacade;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Rest controller for Books.
 * @author mcada
 */

@RestController
@RequestMapping("/books")
public class BookRestController {

    final static Logger logger = LoggerFactory.getLogger(LoanRestController.class);

    @Inject
    private BookFacade bookFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BookDTO> getLoans() throws DataAccessException {
        logger.info("rest find all books");
        return bookFacade.findAll();
    }


}
