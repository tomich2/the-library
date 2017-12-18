package cz.fi.muni.pa165.controller;

/**
 *
 * @author tchomo
 */
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.fi.muni.pa165.facade.MemberFacade;

@Controller
@RequestMapping("/login")
public class LoginController {

    
    MemberFacade memberFacade;
    
    @Inject
    public LoginController(MemberFacade memberFacade) {
        this.memberFacade = memberFacade;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String init(Model model) {
        return "login/login";
    }
}
