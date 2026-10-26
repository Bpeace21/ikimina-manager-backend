package rw.ikimina.ikimina_manager.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ikimina.ikimina_manager.domain.SavingsGroup;

public interface SavingsGroupRepository extends JpaRepository<SavingsGroup, UUID> {
    
}
