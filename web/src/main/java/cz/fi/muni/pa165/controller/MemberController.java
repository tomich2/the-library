    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.controller;

import cz.fi.muni.pa165.dto.LoanDTO;
import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import cz.fi.muni.pa165.dto.MemberDTO;
import cz.fi.muni.pa165.dto.CreateMemberDTO;
import cz.fi.muni.pa165.facade.LoanFacade;
import cz.fi.muni.pa165.facade.MemberFacade;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tchomo
 */
@Controller
@RequestMapping("/member")
public class MemberController {
    
    private static final Logger log = LoggerFactory.getLogger(LoanItemController.class);
    
 
    MemberFacade memberFacade;  
    
    LoanFacade loanFacade;
    
    @Inject
    public MemberController(MemberFacade memberFacade) {
        this.memberFacade = memberFacade;
    }
  
    
    
     @RequestMapping("/{id}")
    public String showMember(@PathVariable long id, Model model) throws DataAccessException {
        MemberDTO dto = memberFacade.findById(id);
        
        if (dto == null)
            return "404";
        List<LoanDTO> allLoans = loanFacade.allLoansOfMember(dto.getId());
//        List<LoanDTO> activeLoans = new ArrayList<>();
//        List<LoanDTO> returnedLoans = new ArrayList<>();
//        for (LoanDTO loan : allLoans) {
//            if (loan.getReturned()) {
//                returnedLoans.add(loan);
//            } else {
//                activeLoans.add(loan);
//            }
//        }
        model.addAttribute("member", dto);
        model.addAttribute("all loans", allLoans);
//        model.addAttribute("returnedloans", returnedLoans);
        return "member/show";
    }
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createMemberView(Model model) {
        model.addAttribute("createMember", new CreateMemberDTO());
        return "member/create";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createMember(@Valid @ModelAttribute CreateMemberDTO dto, BindingResult result, Model model) throws DataAccessException {
        if (result.hasErrors()) {
            return "member/create";
        }
        Long id = memberFacade.registerMember(dto);
        return "redirect:" + id;
    }

}
