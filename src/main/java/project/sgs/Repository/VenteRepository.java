package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.sgs.Entity.Vente;


@Repository
public interface VenteRepository extends JpaRepository<Vente,Long> {
    Vente findVenteById(Long id);

    Vente findVenteByCode(String code);
}
