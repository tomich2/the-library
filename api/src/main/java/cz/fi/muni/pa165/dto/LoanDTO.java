package cz.fi.muni.pa165.dto;

import java.util.Date;
import java.util.List;

/**
 * LoanDTO
 */
public class LoanDTO {
    private Long id;
    private MemberDTO member;
    private List<LoanItemDTO> loanItems;
    private Date loanCreated;
    private Date loanReturned;

    public Long getId() {
        return id;
    }

    public MemberDTO getMember() {
        return member;
    }

    public List<LoanItemDTO> getLoanItems() {
        return loanItems;
    }

    public Date getLoanCreated() {
        return loanCreated;
    }

    public Date getLoanReturned() {
        return loanReturned;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public void setLoanItems(List<LoanItemDTO> loanItems) {
        this.loanItems = loanItems;
    }

    public void setLoanCreated(Date loanCreated) {
        this.loanCreated = loanCreated;
    }

    public void setLoanReturned(Date loanReturned) {
        this.loanReturned = loanReturned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanDTO loanDTO = (LoanDTO) o;

        if (id != null ? !id.equals(loanDTO.id) : loanDTO.id != null) return false;
        if (member != null ? !member.equals(loanDTO.member) : loanDTO.member != null) return false;
        if (loanItems != null ? !loanItems.equals(loanDTO.loanItems) : loanDTO.loanItems != null) return false;
        if (loanCreated != null ? !loanCreated.equals(loanDTO.loanCreated) : loanDTO.loanCreated != null) return false;
        return loanReturned != null ? loanReturned.equals(loanDTO.loanReturned) : loanDTO.loanReturned == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (member != null ? member.hashCode() : 0);
        result = 31 * result + (loanItems != null ? loanItems.hashCode() : 0);
        result = 31 * result + (loanCreated != null ? loanCreated.hashCode() : 0);
        result = 31 * result + (loanReturned != null ? loanReturned.hashCode() : 0);
        return result;
    }
}
