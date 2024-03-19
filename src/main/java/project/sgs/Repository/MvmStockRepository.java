package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.sgs.Entity.MvmStock;
@Repository
public interface MvmStockRepository extends JpaRepository<MvmStock,Long> {
}
