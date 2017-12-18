package cz.fi.muni.pa165.controller;

import cz.fi.muni.pa165.facade.LoanFacade;
import cz.fi.muni.pa165.facade.LoanItemFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @xtlamich
 */

@Controller
@RequestMapping("/loans")
public class LoanController {
    private static final Logger log = LoggerFactory.getLogger(LoanItemController.class);

    private LoanFacade loanFacade;



}
