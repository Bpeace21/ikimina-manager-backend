package rw.ikimina.ikimina_manager.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.ikimina.ikimina_manager.domain.Member;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    List<Member> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}
