package project.sgs.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.sgs.Entity.Article;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String codeArticle;
    private String designation;
    private String photoName;
    private BigDecimal prixUnitairHt;
    private BigDecimal tauxtva;
    private UtlisateureDto utlisateureDto;
    private BigDecimal prixUnitairTtc;
    private String photo;
    private String codeCategorie;
    public static ArticleDto articleFromDto(Article article) {
        return ArticleDto.builder()
                .codeArticle(article.getCodeArticle())
                .id(article.getId())
                .codeCategorie(CategorieDto.DtoFromCategorie(article.getCategorie()).getCodeCategorie())
                .designation(article.getDesignation())
                .photo(article.getPhoto())
                .tauxtva(article.getTauxtva())
                .prixUnitairHt(article.getPrixUnitairHt())
                .prixUnitairTtc(article.getPrixUnitairTtc())
                .photoName(article.getPhotoName())
                .build();
    }
    public static Article DtoToArticle(ArticleDto articleDto){
        return Article.builder()
                .codeArticle(articleDto.getCodeArticle())
                .designation(articleDto.getDesignation())
                .photo(articleDto.getPhoto())
                .tauxtva(articleDto.getTauxtva())
                .prixUnitairTtc(articleDto.getPrixUnitairTtc())
                .prixUnitairHt(articleDto.getPrixUnitairHt())
                .photoName(articleDto.getPhotoName())
                .build();
    }
}
