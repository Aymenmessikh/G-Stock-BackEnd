package project.sgs.Dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import project.sgs.Entity.LigneCommandeFournisseur;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCmndFournissureDto {
    private Long id;
    private ArticleDto articleDto;
    @JsonIgnore
    private CommandefourinsseureDto commandefourinsseureDto;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    public static LigneCommandeFournisseur DtoToLigneCommanfFournisseure(LigneCmndFournissureDto ligneCmndFournissureDto){
        return LigneCommandeFournisseur.builder()
                .article(ArticleDto.DtoToArticle(ligneCmndFournissureDto.getArticleDto()))
                .prixUnitaire(ligneCmndFournissureDto.getPrixUnitaire())
                .quantite(ligneCmndFournissureDto.getQuantite())
                .build();
    }
    public static LigneCmndFournissureDto DtoFromLigneCommandFourniseure(LigneCommandeFournisseur ligneCommandeFournisseur){
        return LigneCmndFournissureDto.builder()
                .commandefourinsseureDto(CommandefourinsseureDto.DtoFromCommandFourinsseure(ligneCommandeFournisseur.getCommandeFournisseur()))
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .id(ligneCommandeFournisseur.getId())
                .articleDto(ArticleDto.articleFromDto(ligneCommandeFournisseur.getArticle()))
                .build();
    }
}
