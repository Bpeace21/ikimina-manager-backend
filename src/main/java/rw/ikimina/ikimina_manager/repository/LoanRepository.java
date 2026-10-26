package rw.ikimina.ikimina_manager.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ikimina.ikimina_manager.domain.Loan;
import rw.ikimina.ikimina_manager.domain.LoanStatus;
import rw.ikimina.ikimina_manager.domain.Treasurer;

public interface LoanRepository extends JpaRepository<Loan, UUID> {

    List<Loan> findByStatusOrderByLoanDateAsc(LoanStatus status);

    List<Loan> findByLoanDateBetweenOrderByLoanDateAsc(LocalDate start, LocalDate end);

    boolean existsByTreasurerAndLoanDateAndStatusNot(Treasurer treasurer, LocalDate loanDate, LoanStatus status);

    @Query("SELECT l.status, COUNT(l) FROM Loan l GROUP BY l.status")
    List<Object[]> statusStats();

    @Modifying
    @Query("UPDATE Loan l SET l.status = :newStatus WHERE l.treasurer.id = :treasurerId AND l.loanDate = :date AND l.status <> :keepStatus")
    int cancelDay(@Param("newStatus") LoanStatus newStatus, @Param("treasurerId") UUID treasurerId,
            @Param("date") LocalDate date, @Param("keepStatus") LoanStatus keepStatus);

    long countByLoanDate(LocalDate loanDate);

    long countByStatus(LoanStatus status);
}
