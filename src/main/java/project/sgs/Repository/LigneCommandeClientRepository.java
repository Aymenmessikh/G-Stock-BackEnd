package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.sgs.Entity.CommandeClient;
import project.sgs.Entity.LigneCommandeClient;

import java.util.List;

@Repository
public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient,Long> {
    public LigneCommandeClient findAllById(Long id);
    public List<LigneCommandeClient> findLigneCommandeClientByCommandeClient(CommandeClient commandeClient);
    List<LigneCommandeClient> findAllByArticleId(Long id);

}
