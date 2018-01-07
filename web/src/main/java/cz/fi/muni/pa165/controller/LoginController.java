package cz.fi.muni.pa165.controller;

/**
 *  Login controller.
 * @author tchomo, xtlamich
 */
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cz.fi.muni.pa165.dto.CreateMemberDTO;
import cz.fi.muni.pa165.dto.MemberAuthenticateDTO;
import cz.fi.muni.pa165.dto.MemberDTO;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.fi.muni.pa165.facade.MemberFacade;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    MemberFacade memberFacade;
    @Autowired
    MemberService memberService;
    
    @Inject
    public LoginController(MemberFacade memberFacade) {
        this.memberFacade = memberFacade;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String init(Model model) {
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(Model model) {
        return "login/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST )
    public String login(@Valid @ModelAttribute("user") MemberAuthenticateDTO memberAuthForm, RedirectAttributes redirectAttributes, BindingResult result, Model model, HttpServletRequest request, UriComponentsBuilder uriBuilder) throws DataAccessException {
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                model.addAttribute(error.getField() + "_error", true);
            }

            model.addAttribute("user", new CreateMemberDTO());
            return "login/login";
        }

        MemberDTO found = memberFacade.findByEmail(memberAuthForm.getMemberEmail());

        if (found == null || !memberFacade.authenticateMember(memberAuthForm)) {
            redirectAttributes.addFlashAttribute("alert_warning", "Login with email " + memberAuthForm.getMemberEmail()
                    + " has failed. Wrong password?");
            return "redirect:" + uriBuilder.path("/login/login").toUriString();
        }

        request.getSession().setAttribute("authenticatedUser", found);


        redirectAttributes.addFlashAttribute("alert_success", "Logged in successfully");
        return "redirect:" + uriBuilder.path("/").toUriString();

    }

    @RequestMapping(value = "/logoff",method = RequestMethod.GET )
    public String logoff(HttpServletRequest request, UriComponentsBuilder uriBuilder) throws DataAccessException {
        request.getSession().removeAttribute("authenticatedUser");
        return "redirect:/";
    }
}
