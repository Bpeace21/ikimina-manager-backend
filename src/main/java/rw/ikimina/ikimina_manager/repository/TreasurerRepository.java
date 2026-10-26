package rw.ikimina.ikimina_manager.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ikimina.ikimina_manager.domain.SavingsGroup;
import rw.ikimina.ikimina_manager.domain.Treasurer;

public interface TreasurerRepository extends JpaRepository<Treasurer, UUID> {

    @Query("SELECT t FROM Treasurer t JOIN t.loanProducts p WHERE LOWER(p.name) = LOWER(:name)")
    List<Treasurer> findByLoanProductName(@Param("name") String name);

    boolean existsBySavingsGroupAndIdNot(SavingsGroup savingsGroup, UUID id);
}