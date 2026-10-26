package rw.ikimina.ikimina_manager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ikimina.ikimina_manager.domain.Loan;
import rw.ikimina.ikimina_manager.domain.LoanStatus;
import rw.ikimina.ikimina_manager.domain.Repayment;
import rw.ikimina.ikimina_manager.repository.LoanRepository;
import rw.ikimina.ikimina_manager.repository.RepaymentRepository;

@Service
public class RepaymentService {

    @Autowired
    private RepaymentRepository repaymentRepo;

    @Autowired
    private LoanRepository loanRepo;

    public String saveRepayment(UUID loanId, Repayment repayment) {
        Optional<Loan> found = loanRepo.findById(loanId);
        if (found.isEmpty()) {
            return "The loan with that id does not exist";
        }
        if (found.get().getStatus() != LoanStatus.DISBURSED) {
            return "Repayments are only for DISBURSED loans";
        }
        repayment.setPaymentDate(LocalDate.now());
        repayment.setLoan(found.get());
        repaymentRepo.save(repayment);
        return "Repayment saved successfully";
    }

    public List<Repayment> getAll() {
        return repaymentRepo.findAll();
    }

    public List<Repayment> getByMemberNumber(String memberNumber) {
        return repaymentRepo.findByMemberNumber(memberNumber);
    }

    public String deleteRepayment(UUID id) {
        Optional<Repayment> found = repaymentRepo.findById(id);
        if (found.isEmpty()) {
            return "The repayment with that id does not exist";
        }
        repaymentRepo.delete(found.get());
        return "Repayment deleted successfully";
    }
}
