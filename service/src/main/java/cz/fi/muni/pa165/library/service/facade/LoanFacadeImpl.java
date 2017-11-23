package cz.fi.muni.pa165.library.service.facade;


import cz.fi.muni.pa165.library.api.dto.CreateLoanDTO;
import cz.fi.muni.pa165.library.api.dto.LoanDTO;
import cz.fi.muni.pa165.library.api.exception.LibraryServiceException;
import cz.fi.muni.pa165.library.api.facade.LoanFacade;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.service.service.BookService;
import cz.fi.muni.pa165.library.service.service.LoanItemService;
import cz.fi.muni.pa165.library.service.service.LoanService;
import cz.fi.muni.pa165.library.service.service.MemberService;
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
    private BookService bookService;

    @Inject
    private LoanItemService loanItemService;


    @Override
    public List<Long> createLoan(CreateLoanDTO loan) throws LibraryServiceException {
        if(loan.getMemberId().isEmpty()){
            throw new LibraryServiceException("No loan book items.");
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
    public List<LoanDTO> allLoansOfMember(Long memberId)throws LibraryServiceException {
        Member member = memberService.findById(memberId);
        if(member == null){
            throw new LibraryServiceException("Member not found.");
        }
        return mapper.map(loanService.allLoansOfMember(member), LoanDTO.class);
    }

    @Override
    public void delete(Long loanId) throws LibraryServiceException {
        Loan loan = loanService.findById(loanId);
        if(loan == null){
            throw new LibraryServiceException("Loan not found");
        }
        loanService.delete(loan);
    }

}
