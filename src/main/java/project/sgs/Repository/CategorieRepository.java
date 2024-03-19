package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.sgs.Entity.Categorie;
@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    public Categorie findCategorieById(Long Id);
    public  Categorie findCategorieByCode(String code);
}
