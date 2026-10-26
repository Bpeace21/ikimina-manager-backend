package rw.ikimina.ikimina_manager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rw.ikimina.ikimina_manager.domain.Loan;
import rw.ikimina.ikimina_manager.domain.LoanStatus;
import rw.ikimina.ikimina_manager.repository.LoanRepository;
import rw.ikimina.ikimina_manager.repository.RepaymentRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepo;

    @Autowired
    private RepaymentRepository repaymentRepo;

    public String saveLoan(Loan loan) {
        if (loan.getLoanDate() == null) {
            return "Loan date is required";
        }
        if (loan.getLoanDate().isBefore(LocalDate.now())) {
            return "Loan date cannot be in the past";
        }
        boolean busy = loanRepo.existsByTreasurerAndLoanDateAndStatusNot(
                loan.getTreasurer(), loan.getLoanDate(), LoanStatus.REJECTED);
        if (busy) {
            return "Treasurer already processes a loan on that date";
        }
        loan.setStatus(LoanStatus.REQUESTED);
        loanRepo.save(loan);
        return "Loan saved successfully";
    }

    public List<Loan> getAll() {
        return loanRepo.findAll();
    }

    public List<Loan> getByStatus(LoanStatus status) {
        return loanRepo.findByStatusOrderByLoanDateAsc(status);
    }

    public List<Loan> getBetween(LocalDate start, LocalDate end) {
        return loanRepo.findByLoanDateBetweenOrderByLoanDateAsc(start, end);
    }

    public Optional<Loan> getById(UUID id) {
        return loanRepo.findById(id);
    }

    public String updateLoan(UUID id, Loan newData) {
        Optional<Loan> found = loanRepo.findById(id);
        if (found.isEmpty()) {
            return "The loan with that id does not exist";
        }
        Loan old = found.get();
        old.setLoanDate(newData.getLoanDate());
        old.setPrincipal(newData.getPrincipal());
        old.setPurpose(newData.getPurpose());
        loanRepo.save(old);
        return "Loan updated successfully";
    }

    public String updateStatus(UUID id, LoanStatus newStatus) {
        Optional<Loan> found = loanRepo.findById(id);
        if (found.isEmpty()) {
            return "The loan with that id does not exist";
        }
        Loan loan = found.get();
        LoanStatus current = loan.getStatus();
        boolean allowed = current == LoanStatus.REQUESTED
                && (newStatus == LoanStatus.APPROVED || newStatus == LoanStatus.REJECTED);
        allowed = allowed || current == LoanStatus.APPROVED
                && (newStatus == LoanStatus.DISBURSED || newStatus == LoanStatus.REJECTED);
        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        loan.setStatus(newStatus);
        loanRepo.save(loan);
        return "Loan status updated successfully";
    }

    public String deleteLoan(UUID id) {
        Optional<Loan> found = loanRepo.findById(id);
        if (found.isEmpty()) {
            return "The loan with that id does not exist";
        }
        if (repaymentRepo.existsByLoan(found.get())) {
            return "That loan has repayments, delete them first";
        }
        loanRepo.delete(found.get());
        return "Loan deleted successfully";
    }

    public List<Object[]> getStatsByStatus() {
        return loanRepo.statusStats();
    }

    @Transactional
    public String cancelDay(UUID treasurerId, LocalDate date) {
        int howMany = loanRepo.cancelDay(LoanStatus.REJECTED, treasurerId, date, LoanStatus.DISBURSED);
        return howMany + " loans cancelled";
    }

    public long getLoanCountByDate(LocalDate loanDate) {
        return loanRepo.countByLoanDate(loanDate);
    }

    public long getLoanCountByStatus(LoanStatus status) {
        return loanRepo.countByStatus(status);
    }

    public Page<Loan> getPage(Pageable pageable) {
        return loanRepo.findAll(pageable);
    }
}
