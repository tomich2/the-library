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
import cz.fi.muni.pa165.facade.MemberFacade;
import java.util.List;

/**
 *
 * @author tchomo
 */
@Controller
@RequestMapping("/member")
public class MemberController {
    
    @Inject
    MemberFacade facade;            ;
    
    @RequestMapping("/{id}")
    public String showMember(@PathVariable long id, Model model) {
        MemberDTO dto = facade.findById(id);
        if (dto == null)
            return "404";
//        List<LoanDTO> allLoans = facade.getAllLoans(dto.getId());
//        System.out.println("Number of loans: " + allLoans.size());
//        model.addAttribute("member", dto);
//        model.addAttribute("loans", allLoans);
        return "member/show";
    }

}
