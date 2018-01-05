package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.dto.BookDTO;
import cz.fi.muni.pa165.facade.BookFacade;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Rest controller for Books.
 * @author mcada
 */

@RestController
@RequestMapping("/books")
public class BookRestController {

    final static Logger logger = LoggerFactory.getLogger(BookRestController.class);

    @Inject
    private BookFacade bookFacade;

    /**
     * Returns all books as JSON.
     *
     * @return List of BookDTOs as JSON
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<BookDTO> getBooks() {
        return bookFacade.findAll();
    }

    /**
     * Returns Book with the given id as JSON.
     *
     * @param id ID of the book to return
     * @return BookDTO as JSON
     * @throws ResourceNotFoundException Thrown when book with given ID is not found
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final BookDTO getBook(@PathVariable("id") long id) throws ResourceNotFoundException {
        return resolveNotFound(bookFacade.findById(id));
    }

    /**
     * Creates a book with the given information.
     *
     * @param book BookDTO
     * @return Created book
     * @throws ResourceNotFoundException Thrown when resource already exists
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final BookDTO createBook(@RequestBody BookDTO book) throws ResourceNotFoundException {
        long id = bookFacade.create(book);
        return bookFacade.findById(id);
    }

    /**
     * Updates book with the given ID.
     *
     * @param book Book with the updated information
     * @param id     ID of the book to update
     * @return Updated book
     * @throws ResourceNotFoundException Thrown when resource already exists
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final BookDTO updateBook(@RequestBody BookDTO book,
                                    @PathVariable("id") long id) throws ResourceNotFoundException {

        book.setId(id);
        bookFacade.update(book);
        return bookFacade.findById(book.getId());

    }

    /**
     * Deletes a book with the given ID.
     *
     * @param id ID of the book to delete.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteBook(@PathVariable("id") long id) {
        logger.info("Preparing to delete book");
        resolveNotFound(bookFacade.findById(id));
        bookFacade.delete(id);
    }

    /**
     * Throws ResourceNotFoundException if object is null. Otherwise returns object
     *
     * @param object Object to check if null
     * @param <T>    Type of the object
     * @return Given object if it's not null
     */
    private <T> T resolveNotFound(T object) {
        if (object == null)
            throw new ResourceNotFoundException();
        return object;
    }
}
