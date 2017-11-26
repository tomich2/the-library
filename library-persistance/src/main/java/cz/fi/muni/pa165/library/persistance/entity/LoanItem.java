package cz.fi.muni.pa165.library.persistance.entity;

import cz.fi.muni.pa165.library.persistance.entity.base.EntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Entity representing relationship between Loan and Item entities
 *
 * @author mcada
 */
@Entity
public class LoanItem implements EntityBase{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Book book;

    @ManyToOne
    private Loan loan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) {
            return false;
        }

        LoanItem loanItem = (LoanItem) o;

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