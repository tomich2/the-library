package cz.fi.muni.pa165.dto;



/**
 * Data transfer object for LoanItem.
 *
 * @author Michael Cada
 */
public class LoanItemDTO {

    private Long id;

    private BookDTO book;

    private LoanDTO loan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public LoanDTO getLoan() {
        return loan;
    }

    public void setLoan(LoanDTO loan) {
        this.loan = loan;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanItemDTO)) return false;

        LoanItemDTO loanItem = (LoanItemDTO) o;

        if (!getId().equals(loanItem.getId())) return false;
        if (!getBook().equals(loanItem.getBook())) return false;
        return getLoan() != null ? getLoan().equals(loanItem.getLoan()) : loanItem.getLoan() == null;
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = 31 * result + getBook().hashCode();
        result = 31 * result + (getLoan() != null ? getLoan().hashCode() : 0);
        return result;
    }
}
