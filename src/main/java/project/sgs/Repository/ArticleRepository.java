package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.sgs.Entity.Article;
import project.sgs.Entity.Categorie;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    public Article findArticleById(Long id);
    public Article findArticleByCodeArticle(String codeArticle);

    List<Article> findAllArticleByCategorie(Categorie categorie);
}
