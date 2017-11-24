package cz.fi.muni.pa165.dto;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * Loan create DTO.
 */
public class CreateLoanDTO {

    @NotBlank
    private Long memberId;
    @NotNull
    private Set<Long> loanitemIds;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Set<Long> getLoanitemIds() {
        return loanitemIds;
    }

    public void setLoanitemIds(Set<Long> loanitemIds) {
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
