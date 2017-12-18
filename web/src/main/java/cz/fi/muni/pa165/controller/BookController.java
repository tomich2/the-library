package cz.fi.muni.pa165.controller;

import cz.fi.muni.pa165.dto.BookDTO;
import cz.fi.muni.pa165.facade.BookFacade;
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
@RequestMapping("/books")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookFacade bookFacade;

    @Inject
    public BookController(BookFacade bookFacade) {
        this.bookFacade = bookFacade;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", bookFacade.findAll());
        log.info("inside of bookController, trying to access list");
        return "books/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createNewBook(Model model) {
        model.addAttribute("BookCreate", new BookDTO() );
        return "books/create";
    }




    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("book", bookFacade.findById(id));
        return "books/view";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("BookCreate") BookDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(BookCreate={})", formBean);
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "books/create";
        }
        long id = bookFacade.create(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Book " + id + " was created");
        return "redirect:" + uriBuilder.path("/books/list").toUriString();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        BookDTO book = bookFacade.findById(id);
        bookFacade.delete(id);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Book with id\"" + book.getId() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/books/list").toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable long id, Model model) {
        BookDTO book = bookFacade.findById(id);
        if (book == null) {
            return "redirect:/books/list";
        }
        model.addAttribute("book", book);
        return "books/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updatebook(@ModelAttribute("book") BookDTO book, @PathVariable("id") long id,
                                 Model model, UriComponentsBuilder uriBuilder) {

        book.setId(id);
        bookFacade.update(book);
        log.debug(book.toString() + " updated");
        return "redirect:" + uriBuilder.path("/books/list").toUriString();
    }
}
