package project.sgs.Dto;
import lombok.Builder;
import lombok.Data;
import project.sgs.Entity.LigneVente;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {
    private Long id;
    private VenteDto venteDto;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private ArticleDto articleDto;
    public static LigneVente DtoToLigneVente(LigneVenteDto ligneVenteDto){
        return LigneVente.builder()
                .article(ArticleDto.DtoToArticle(ligneVenteDto.getArticleDto()))
                .prixUnitaire(ligneVenteDto.getPrixUnitaire())
                .quantite(ligneVenteDto.getQuantite())
                .build();
    }
    public static LigneVenteDto DtoFromLigneVentes(LigneVente ligneVente){
        return LigneVenteDto.builder()
                .articleDto(ArticleDto.articleFromDto(ligneVente.getArticle()))
                .id(ligneVente.getId())
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .build();
    }
}
