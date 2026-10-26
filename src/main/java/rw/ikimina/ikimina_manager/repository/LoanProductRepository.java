package rw.ikimina.ikimina_manager.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rw.ikimina.ikimina_manager.domain.LoanProduct;

@Repository
public interface LoanProductRepository extends JpaRepository<LoanProduct, UUID> {

    @Query("SELECT p FROM LoanProduct p WHERE p.treasurers IS EMPTY")
    List<LoanProduct> findUnused();
}
