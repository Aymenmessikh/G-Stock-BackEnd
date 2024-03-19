package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.sgs.Entity.LigneCommandeFournisseur;

import java.util.List;

@Repository
public interface LigneCommandeFournisseureRepository extends JpaRepository<LigneCommandeFournisseur,Long> {
    List<LigneCommandeFournisseur> findAllByArticleId(Long id);
}
