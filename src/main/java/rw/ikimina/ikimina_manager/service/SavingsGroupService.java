package rw.ikimina.ikimina_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ikimina.ikimina_manager.domain.SavingsGroup;
import rw.ikimina.ikimina_manager.repository.SavingsGroupRepository;

@Service 
public class SavingsGroupService {
    
    @Autowired 
    private SavingsGroupRepository savingsGroupRepo;

    public String saveSavingsGroup(SavingsGroup savingsGroup) {
    savingsGroupRepo.save(savingsGroup);
        return "Savings group saved successfully";
    }

    public List<SavingsGroup> getAll() {
        return savingsGroupRepo.findAll();
    }
    public Optional<SavingsGroup> getById(UUID id) {
        return savingsGroupRepo.findById(id);
    }
    public String updateSavingsGroup(UUID id, SavingsGroup newData) {
        Optional<SavingsGroup> found = savingsGroupRepo.findById(id);
        if (found.isEmpty()) {
            return "The savings group with that id does not exist";
        }
        SavingsGroup old = found.get();
        old.setName(newData.getName());
        savingsGroupRepo.save(old);
        return "Savings group updated successfully";
    }
    public String deleteSavingsGroup(UUID id) {
        Optional<SavingsGroup> found = savingsGroupRepo.findById(id);
        if (found.isEmpty()) {
            return "The savings group with that id does not exist";
        }
        savingsGroupRepo.deleteById(id);
        return "Savings group deleted successfully";
    }

}