package rw.ikimina.ikimina_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ikimina.ikimina_manager.domain.Treasurer;
import rw.ikimina.ikimina_manager.repository.TreasurerRepository;

@Service
public class TreasurerService {
    @Autowired
    private TreasurerRepository treasurerRepo;

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