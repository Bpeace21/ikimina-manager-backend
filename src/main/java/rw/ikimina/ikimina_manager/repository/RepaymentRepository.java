package rw.ikimina.ikimina_manager.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ikimina.ikimina_manager.domain.Loan;
import rw.ikimina.ikimina_manager.domain.Repayment;

public interface RepaymentRepository extends JpaRepository<Repayment, UUID> {

    @Query("SELECT d FROM Repayment d WHERE d.loan.member.memberNumber = :memberNumber ORDER BY d.paymentDate DESC")
    List<Repayment> findByMemberNumber(@Param("memberNumber") String memberNumber);

    boolean existsByLoan(Loan loan);
}
