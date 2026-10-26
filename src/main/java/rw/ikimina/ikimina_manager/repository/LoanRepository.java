package rw.ikimina.ikimina_manager.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.ikimina.ikimina_manager.domain.Loan;
import rw.ikimina.ikimina_manager.domain.LoanStatus;

public interface LoanRepository extends JpaRepository<Loan, UUID> {

    List<Loan> findByStatusOrderByLoanDateAsc(LoanStatus status);

    List<Loan> findByLoanDateBetweenOrderByLoanDateAsc(LocalDate start, LocalDate end);
}
