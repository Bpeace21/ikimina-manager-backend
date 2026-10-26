package rw.ikimina.ikimina_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ikimina.ikimina_manager.domain.Loan;
import rw.ikimina.ikimina_manager.repository.LoanRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepo;

    public String saveLoan(Loan loan) {
        loanRepo.save(loan);
        return "Loan saved successfully";
    }

    public List<Loan> getAll() {
        return loanRepo.findAll();
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
        old.setStatus(newData.getStatus());
        loanRepo.save(old);
        return "Loan updated successfully";
    }

    public String deleteLoan(UUID id) {
        Optional<Loan> found = loanRepo.findById(id);
        if (found.isEmpty()) {
            return "The loan with that id does not exist";
        }
        loanRepo.delete(found.get());
        return "Loan deleted successfully";
    }
}
