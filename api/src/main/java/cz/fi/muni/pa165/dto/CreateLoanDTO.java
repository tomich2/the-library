package cz.fi.muni.pa165.dto;


import java.util.List;

/**
 * Loan create DTO.
 */
public class CreateLoanDTO {

    private List<Long> memberId;
    private List<List<Long> > loanitemIds;

    public List<Long> getMemberId() {
        return memberId;
    }

    public void setMemberId(List<Long> memberId) {
        this.memberId = memberId;
    }

    public List<List<Long>> getLoanitemIds() {
        return loanitemIds;
    }

    public void setLoanitemIds(List<List<Long>> loanitemIds) {
        this.loanitemIds = loanitemIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateLoanDTO that = (CreateLoanDTO) o;

        if (memberId != null ? !memberId.equals(that.memberId) : that.memberId != null) return false;
        return loanitemIds != null ? loanitemIds.equals(that.loanitemIds) : that.loanitemIds == null;
    }

    @Override
    public int hashCode() {
        int result = memberId != null ? memberId.hashCode() : 0;
        result = 31 * result + (loanitemIds != null ? loanitemIds.hashCode() : 0);
        return result;
    }
}
