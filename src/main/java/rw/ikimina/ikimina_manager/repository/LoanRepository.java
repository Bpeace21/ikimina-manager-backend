package rw.ikimina.ikimina_manager.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.ikimina.ikimina_manager.domain.Loan;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
}
