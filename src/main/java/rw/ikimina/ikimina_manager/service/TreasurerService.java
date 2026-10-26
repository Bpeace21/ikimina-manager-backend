package rw.ikimina.ikimina_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rw.ikimina.ikimina_manager.domain.LoanProduct;
import rw.ikimina.ikimina_manager.domain.SavingsGroup;
import rw.ikimina.ikimina_manager.domain.Treasurer;
import rw.ikimina.ikimina_manager.repository.LoanProductRepository;
import rw.ikimina.ikimina_manager.repository.SavingsGroupRepository;
import rw.ikimina.ikimina_manager.repository.TreasurerRepository;

@Service
public class TreasurerService {

    @Autowired
    private TreasurerRepository treasurerRepo;

    @Autowired
    private LoanProductRepository loanProductRepo;

    @Autowired
    private SavingsGroupRepository savingsGroupRepo;

    public String saveTreasurer(Treasurer treasurer) {
        treasurerRepo.save(treasurer);
        return "Treasurer saved successfully";
    }
        public List<Treasurer> getAll() {
            return treasurerRepo.findAll();
    }
    public Optional<Treasurer> getById(UUID id) {
        return treasurerRepo.findById(id);
    }

    public List<Treasurer> getByLoanProduct(String name) {
        return treasurerRepo.findByLoanProductName(name);
    }

    @Transactional
    public String addLoanProduct(UUID treasurerId, UUID loanProductId) {
        Optional<Treasurer> t = treasurerRepo.findById(treasurerId);
        if (t.isEmpty()) {
            return "The treasurer with that id does not exist";
        }
        Optional<LoanProduct> p = loanProductRepo.findById(loanProductId);
        if (p.isEmpty()) {
            return "That loan product does not exist";
        }
        Treasurer treasurer = t.get();
        if (treasurer.getLoanProducts().contains(p.get())) {
            return "The treasurer already has that loan product";
        }
        treasurer.getLoanProducts().add(p.get());
        treasurerRepo.save(treasurer);
        return "Loan product added to the treasurer";
    }

    public String changeSavingsGroup(UUID treasurerId, int groupNumber) {
        Optional<Treasurer> t = treasurerRepo.findById(treasurerId);
        if (t.isEmpty()) {
            return "The treasurer with that id does not exist";
        }
        Optional<SavingsGroup> g = savingsGroupRepo.findByGroupNumber(groupNumber);
        if (g.isEmpty()) {
            return "The savings group with that groupNumber does not exist";
        }
        if (treasurerRepo.existsBySavingsGroupAndIdNot(g.get(), treasurerId)) {
            return "That savings group is taken by another treasurer";
        }
        Treasurer treasurer = t.get();
        treasurer.setSavingsGroup(g.get());
        treasurerRepo.save(treasurer);
        return "Savings group changed successfully";
    }

    public String updateTreasurer(UUID id, Treasurer newData) {
        Optional<Treasurer> found = treasurerRepo.findById(id);
        if (found.isEmpty()) {
            return "The treasurer with that id does not exist";
        }
        Treasurer old = found.get();
        old.setFirstName(newData.getFirstName());
        old.setLastName(newData.getLastName());
        old.setPhone(newData.getPhone());
        treasurerRepo.save(old);
        return "Treasurer updated successfully";
    }
    public String deleteTreasurer(UUID id) {
        Optional<Treasurer> found = treasurerRepo.findById(id);
        if (found.isEmpty()) {
            return "The treasurer with that id does not exist";
        }
        treasurerRepo.delete(found.get());
        return "Treasurer deleted successfully";
    }
}