package rw.ikimina.ikimina_manager.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ikimina.ikimina_manager.domain.LoanProduct;

@Repository
public interface LoanProductRepository extends JpaRepository<LoanProduct, UUID> {
    
}
