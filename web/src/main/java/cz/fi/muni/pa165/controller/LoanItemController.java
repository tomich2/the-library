package cz.fi.muni.pa165.controller;

import cz.fi.muni.pa165.dto.LoanItemDTO;
import cz.fi.muni.pa165.facade.LoanItemFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;


@Controller
@RequestMapping("/loanItems")
public class LoanItemController {

    private static final Logger log = LoggerFactory.getLogger(LoanItemController.class);

    @Autowired
    private LoanItemFacade loanItemFacade;

    @Inject
    public LoanItemController(LoanItemFacade loanItemFacade) {
        this.loanItemFacade = loanItemFacade;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("loanItems", loanItemFacade.findAll());
        log.info("inside of loanItemController, trying to access list");
        return "loanItems/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createNewLoanItem(Model model) {
        model.addAttribute("LoanItemCreate", new LoanItemDTO() {
        });
        return "loanItems/create";
    }




    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("loanItem", loanItemFacade.findById(id));
        return "loanItems/view";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("LoanItemCreate") LoanItemDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(LoanItemCreate={})", formBean);
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "loanItems/create";
        }
        long id = loanItemFacade.create(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "LoanItem " + id + " was created");
        return "redirect:" + uriBuilder.path("/loanItems/list").toUriString();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        LoanItemDTO loanItem = loanItemFacade.findById(id);
        loanItemFacade.delete(id);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "LoanItem with id\"" + loanItem.getId() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/loanItems/list").toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateLoanItem(@PathVariable long id, Model model) {
        LoanItemDTO loanItem = loanItemFacade.findById(id);
        if (loanItem == null) {
            return "redirect:/loanItems/list";
        }
        model.addAttribute("loanItem", loanItem);
        return "loanItems/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateloanItem(@ModelAttribute("loanItem") LoanItemDTO loanItem, @PathVariable("id") long id,
                               Model model, UriComponentsBuilder uriBuilder) {

        loanItem.setId(id);
        loanItemFacade.update(loanItem);
        log.debug(loanItem.toString() + " updated");
        return "redirect:" + uriBuilder.path("/loanItems/list").toUriString();
    }
}
