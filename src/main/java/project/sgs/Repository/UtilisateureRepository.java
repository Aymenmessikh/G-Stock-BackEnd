package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.sgs.Entity.Utlisateure;

import java.util.Optional;

@Repository
public interface UtilisateureRepository extends JpaRepository<Utlisateure,Long> {
    public Utlisateure findUtilisateureById(Long id);

    Optional<Utlisateure> findByEmail(String email);
    public Utlisateure findUtlisateureByEmail(String email);
}
