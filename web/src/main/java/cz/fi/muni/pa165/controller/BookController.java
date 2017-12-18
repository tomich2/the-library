/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.controller;


import cz.fi.muni.pa165.dto.BookDTO;

import cz.fi.muni.pa165.exception.LibraryServiceException;
import cz.fi.muni.pa165.facade.BookFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import org.omg.CosNaming.NamingContextPackage.NotFound;
/**
 *
 * @author tchomo
 */
@Controller
@RequestMapping("/books")
public class BookController {
    final static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Inject
    private BookFacade bookFacade;

    @RequestMapping(value = "/list")
    public String getAll(Model model) {

        model.addAttribute("books", bookFacade.findAll());
        return "book/show_all";
    }
      @RequestMapping(value = "/{id}")
    public String getBook(@PathVariable long id, Model model) throws Exception {

        logger.debug("rest getBook({})", id);

        BookDTO book = bookFacade.findById(id);
        if (book == null) {
            throw new NotFound();
        }
        model.addAttribute("book", book);
        return "book/show_book";
    }
    
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBook(@Valid @ModelAttribute("createBook") BookDTO dto, BindingResult result,
                             Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAtrrs) {
        if (result.hasErrors()) {
            redirectAtrrs.addFlashAttribute("alert_warning", "Book couldn't be created. Invalid parameters.");
            return "redirect:" + uriBuilder.path("books/create").toUriString();
        }
        Long id = bookFacade.create(dto);
        redirectAtrrs.addFlashAttribute("alert_success", "Book was successfuly created");
        return "redirect:" + uriBuilder.path("/books/{id}").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createBook(Model model) {
        model.addAttribute("createBook", new BookDTO());
        return "book/create_book";
    }
    
    @RequestMapping(path = "delete/{id}", method = RequestMethod.POST)
    public String deleteCollection(@PathVariable long id) {
        bookFacade.delete(id);
        return "redirect:/books/list";
    }
    
}
