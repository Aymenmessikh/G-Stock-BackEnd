package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.sgs.Dto.TopClient;
import project.sgs.Entity.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
    Client findClientById(Long id);
    Client findClientByMail(String mail);

    @Query("SELECT new project.sgs.Dto.TopClient(A.nome, SUM(B.MontantsCommande))\n" +
            "FROM Client AS A JOIN CommandeClient AS B ON A.id = B.client.id\n" +
            "GROUP BY A.nome\n" +
            "ORDER BY SUM(B.MontantsCommande) DESC\n" +
            "LIMIT 8\n")
    List<TopClient> getTotalMontantsByClient();
}

