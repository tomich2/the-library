package cz.fi.muni.pa165.controller;

import cz.fi.muni.pa165.dto.BookDTO;
import cz.fi.muni.pa165.dto.CreateBookDTO;
import cz.fi.muni.pa165.facade.BookFacade;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
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

/**
 * @author xchomo, mcada
 */
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


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);  
        model.addAttribute("book", bookFacade.findById(id));
        return "books/view";
    }


    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createBookView(Model model) {
        if (!model.containsAttribute("books")) {
            model.addAttribute("books", new CreateBookDTO());
        }
        model.addAttribute("action", "Create");
        return "books/create";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBook(@Valid @ModelAttribute("books") CreateBookDTO dto, BindingResult result, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes
                             ) throws DataAccessException {
        
        Long id = bookFacade.create(dto);
       return "redirect:" + uriBuilder.path("/books/view/{id}").buildAndExpand(id).encode().toUriString();
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
