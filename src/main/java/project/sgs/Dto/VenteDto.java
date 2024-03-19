package project.sgs.Dto;
import lombok.Builder;
import lombok.Data;
import project.sgs.Entity.Vente;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VenteDto {
    private Long id;
    private String code;
    private Instant dateVente;
    private String commentaire;
    private List<LigneVenteDto> ligneVenteDtos;
    public static Vente DtoToVente(VenteDto venteDto){
        return Vente.builder()
                .dateVente(venteDto.getDateVente())
                .code(venteDto.getCode())
                .commentaire(venteDto.getCommentaire())
                .build();
    }
    public static VenteDto DtoFromVente(Vente vente){
        return VenteDto.builder()
                .dateVente(vente.getDateVente())
                .commentaire(vente.getCommentaire())
                .id(vente.getId())
                .code(vente.getCode())
                .build();
    }
}
