package cz.fi.muni.pa165.facade;


import cz.fi.muni.pa165.dto.CreateLoanDTO;
import cz.fi.muni.pa165.dto.LoanDTO;
import cz.fi.muni.pa165.exception.LibraryServiceException;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.service.LoanItemService;
import cz.fi.muni.pa165.service.LoanService;
import cz.fi.muni.pa165.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class LoanFacadeImpl implements LoanFacade {
    @Inject
    private ListMapper mapper;

    @Inject
    private LoanService loanService;

    @Inject
    private MemberService memberService;

    @Inject
    private LoanItemService loanItemService;


    @Override
    public List<Long> createLoan(CreateLoanDTO loan) throws IllegalArgumentException {
        if(loan.getMemberId().isEmpty()){
            throw new IllegalArgumentException("No loan book items.");
        }
        List<List<Long> > loanItems = loan.getLoanitemIds();
        List<Long> createdLoans = new ArrayList<>();
        int i = 0;
        for(Long membersId : loan.getMemberId()){
            Loan newLoan = mapper.map(loan, Loan.class);
            newLoan.setMember(memberService.findById(membersId));
            newLoan.setLoanCreated(new Date());
            List<LoanItem> loanItemList = new ArrayList<>();
            for(Long loanItemId : loanItems.get(i)){
                LoanItem item = loanItemService.findById(loanItemId);
                loanItemList.add(item);
            }
            loanService.create(newLoan);
            createdLoans.add(newLoan.getId());
            i++;
        }
        return createdLoans;
    }

    @Override
    public LoanDTO findById(Long id) {
        return mapper.map(loanService.findById(id), LoanDTO.class);
    }

    @Override
    public List<LoanDTO> findAll() {
        return mapper.map(loanService.findAll(), LoanDTO.class);
    }

    @Override
    public List<LoanDTO> allLoansOfMember(Long memberId)throws IllegalArgumentException {
        Member member = memberService.findById(memberId);
        if(member == null){
            throw new IllegalArgumentException("Member not found.");
        }
        return mapper.map(loanService.allLoansOfMember(member), LoanDTO.class);
    }

    @Override
    public void delete(Long loanId) throws IllegalArgumentException {
        Loan loan = loanService.findById(loanId);
        if(loan == null){
            throw new IllegalArgumentException("Loan not found");
        }
        loanService.delete(loan);
    }

}
