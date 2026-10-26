package rw.ikimina.ikimina_manager.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ikimina.ikimina_manager.domain.Treasurer;

public interface TreasurerRepository extends JpaRepository<Treasurer, UUID> {
    
}