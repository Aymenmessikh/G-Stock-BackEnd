package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.sgs.Entity.CommandeFournisseur;
@Repository
public interface CommandeFournisseureRepository extends JpaRepository<CommandeFournisseur,Long> {
    CommandeFournisseur findCommandeFournisseurById(Long id);

    CommandeFournisseur findCommandeFournisseurByCode(String code);
}
