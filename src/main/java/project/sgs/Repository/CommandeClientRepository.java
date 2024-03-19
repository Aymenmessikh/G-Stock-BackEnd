package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.sgs.Entity.CommandeClient;

@Repository
public interface CommandeClientRepository extends JpaRepository<CommandeClient,Long> {
    CommandeClient findCommandeClientById(Long id);

    CommandeClient findCommandeClientByCode(String code);
}
