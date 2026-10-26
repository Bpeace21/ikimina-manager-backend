package rw.ikimina.ikimina_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ikimina.ikimina_manager.domain.LoanProduct;
import rw.ikimina.ikimina_manager.repository.LoanProductRepository;

@Service
public class LoanProductService {

    @Autowired
    private LoanProductRepository loanProductRepo;

    public String saveLoanProduct(LoanProduct loanProduct) {
        loanProductRepo.save(loanProduct);
        return "Loan product saved successfully";
    }

    public List<LoanProduct> getAll() {
        return loanProductRepo.findAll();
    }

    public Optional<LoanProduct> getById(UUID id) {
        return loanProductRepo.findById(id);
    }

    public String updateLoanProduct(UUID id, LoanProduct newData) {
        Optional<LoanProduct> found = loanProductRepo.findById(id);
        if (found.isEmpty()) {
            return "The loan product with that id does not exist";
        }
        LoanProduct old = found.get();
        old.setName(newData.getName());
        loanProductRepo.save(old);
        return "Loan product updated successfully";
    }

    public String deleteLoanProduct(UUID id) {
        Optional<LoanProduct> found = loanProductRepo.findById(id);
        if (found.isEmpty()) {
            return "The loan product with that id does not exist";
        }
        loanProductRepo.delete(found.get());
        return "Loan product deleted successfully";
    }
}