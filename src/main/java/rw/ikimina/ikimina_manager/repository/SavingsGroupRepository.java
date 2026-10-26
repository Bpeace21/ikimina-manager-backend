package rw.ikimina.ikimina_manager.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rw.ikimina.ikimina_manager.domain.SavingsGroup;

public interface SavingsGroupRepository extends JpaRepository<SavingsGroup, UUID> {

    @Query("SELECT s FROM SavingsGroup s WHERE NOT EXISTS (SELECT t FROM Treasurer t WHERE t.savingsGroup = s) ORDER BY s.groupNumber ASC")
    List<SavingsGroup> findFreeGroups();

    Optional<SavingsGroup> findByGroupNumber(int groupNumber);
}
