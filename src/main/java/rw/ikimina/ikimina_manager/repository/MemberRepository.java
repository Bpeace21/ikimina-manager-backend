package rw.ikimina.ikimina_manager.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ikimina.ikimina_manager.domain.Member;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    List<Member> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT m FROM Loan l JOIN l.member m WHERE l.treasurer.id = :treasurerId")
    List<Member> findMembersOfTreasurer(@Param("treasurerId") UUID treasurerId);
}
