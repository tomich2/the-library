package cz.fi.muni.pa165.facade;


import cz.fi.muni.pa165.dto.CreateLoanDTO;
import cz.fi.muni.pa165.dto.LoanDTO;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.LoanItemService;
import cz.fi.muni.pa165.service.LoanService;
import cz.fi.muni.pa165.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

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
    public Long createLoan(CreateLoanDTO loan) throws DataAccessException, IllegalArgumentException {
        Member member = memberService.findById(loan.getMemberId());
        if(member == null || loan.getLoanitemIds().isEmpty()){
            throw new IllegalArgumentException("No loan book items.");
        }
        Loan newLoan = mapper.map(loan, Loan.class);
        newLoan.setMember(member);
        newLoan.setLoanCreated(new Date());
        Set<LoanItem> loanItemList = new HashSet<>();
        for(Long loanItemId : loan.getLoanitemIds()){
            LoanItem item = loanItemService.findById(loanItemId);
            loanItemList.add(item);
        }
        newLoan.setLoanItems(loanItemList);
        loanService.create(newLoan);
        return newLoan.getId();
    }

    @Override
    public LoanDTO findById(Long id) throws DataAccessException {
        return mapper.map(loanService.findById(id), LoanDTO.class);
    }

    @Override
    public List<LoanDTO> findAll() throws DataAccessException {
        return mapper.map(loanService.findAll(), LoanDTO.class);
    }

    @Override
    public List<LoanDTO> allLoansOfMember(Long memberId)throws DataAccessException {
        Member member = memberService.findById(memberId);
        return mapper.map(loanService.allLoansOfMember(member), LoanDTO.class);
    }

    @Override
    public void delete(Long loanId) throws DataAccessException {
        Loan loan = loanService.findById(loanId);
        loanService.delete(loan);
    }

}
