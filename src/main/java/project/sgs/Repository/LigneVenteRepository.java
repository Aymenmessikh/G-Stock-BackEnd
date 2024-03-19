package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.sgs.Entity.LigneVente;

import java.util.List;
@Repository
public interface LigneVenteRepository extends JpaRepository<LigneVente,Long> {
   /* @Query("select name from article where codeArticle= :code and name= :name")
    List<Article> findByCustemerq(String code,String name);
    //List<Article> findByCustemer(@Param("code") String c, String name);
    @Query(value = "select name from article where codeArticle= :code and name= :name",nativeQuery = true)
    List<Article> findByCustemernativeq(String code,String name);
    List<Article> findByCodeArticleAndDesignation(String codeArticle,String designation);
    //si je veux get les artcile et ingore magiscule ou miniscule en fai comme ca
    List<Article> findByCodeArticleIgnoreCaseAndDesignationIgnoreCase(String codeArticle,String designation);*/
    List<LigneVente> findAllByArticleId(Long id);
}
