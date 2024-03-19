package project.sgs.Dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.sgs.Entity.LigneCommandeClient;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LigneCmndClientDto {
    private Long id;
    private String codeArticle;
   // private ArticleDto articleDto;
    @JsonIgnore
    private CommandeClientDto commandeClientDto;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    public static LigneCommandeClient DtoToLigneCommandClient(LigneCmndClientDto ligneCmndClientDto){
        return LigneCommandeClient.builder()
                .prixUnitaire(ligneCmndClientDto.getPrixUnitaire())
                .quantite(ligneCmndClientDto.getQuantite())
                .build();
    }
    public static LigneCmndClientDto DtoFromLigneCommandClient(LigneCommandeClient ligneCommandeClient){
        return LigneCmndClientDto.builder()
                .commandeClientDto(CommandeClientDto.DtoFromCmmandeClient(ligneCommandeClient.getCommandeClient()))
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .id(ligneCommandeClient.getId())
                .codeArticle(ArticleDto.articleFromDto(ligneCommandeClient.getArticle()).getCodeArticle())
                .build();
    }
}
