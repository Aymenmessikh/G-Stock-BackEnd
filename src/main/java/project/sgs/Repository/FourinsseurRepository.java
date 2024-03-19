package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.sgs.Entity.Fournisseur;
@Repository
public interface FourinsseurRepository extends JpaRepository<Fournisseur,Long> {
    public Fournisseur findFournisseurById(Long id);

    Fournisseur findFournisseurByMail(String mail);
}
