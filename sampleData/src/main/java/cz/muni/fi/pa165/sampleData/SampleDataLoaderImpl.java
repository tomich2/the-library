package cz.muni.fi.pa165.sampleData;

import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.BookService;
import cz.fi.muni.pa165.service.LoanItemService;

import cz.fi.muni.pa165.service.LoanService;
import cz.fi.muni.pa165.service.MemberService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 * @author mcada
 */
@Named
@Transactional
public class SampleDataLoaderImpl implements SampleDataLoader {

	@Inject
	private BookService bookService;

	@Inject
	private LoanItemService loanItemService;

	@Inject
	private MemberService memverService;

	@Inject
	private LoanService loanService;

	@Override
	public void loadData() throws IOException, ParseException, DataAccessException {
		Book book = new Book();
		book.setAuthor("someAuthor");
		book.setTitle("someTitle");
		bookService.create(book);

		Member mem = new Member();
		mem.setEmail("firstName");
		mem.setSurname("surname");
		mem.setEmail("a@a.com");
		mem.setAddress("some address");

		memverService.registerMember(mem, "password");

		Loan loan = new Loan();
		loan.setMember(mem);

		LoanItem loanItem = new LoanItem();
		loanItem.setBook(book);
		loanItem.setLoan(loan);

	}

}
