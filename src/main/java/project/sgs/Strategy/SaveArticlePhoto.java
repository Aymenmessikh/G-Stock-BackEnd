package project.sgs.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.sgs.Dto.ArticleDto;
import project.sgs.Repository.ArticleRepository;
@Service("articleStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveArticlePhoto implements Strategy<ArticleDto>{
    @Autowired
    private final ArticleRepository articleRepository;
    @Override
    public ArticleDto savePhoto(String imageName,String mail, String path) {
        return null;
    }
    /* @Override
     public ArticleDto savePhoto(Long id, String path) {
        ArticleDto article=articelService.getArticleById(id);
        article.setPhoto(path);
        article.setPhotoName(imageName);
        return articelService.saveArticle(article, token);
    }*/
}
