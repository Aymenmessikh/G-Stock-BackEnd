package project.sgs.Dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.sgs.Entity.CommandeClient;
import project.sgs.Entity.EtatCommand;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static project.sgs.Dto.ClientDto.DtoToClient;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandeClientDto {
    private Long id;
    private String code;
    private Instant dateCommande;
    private String mail;
    private EtatCommand etatCommand;
    private List<LigneCmndClientDto> ligneCmndClientDtos;
    private BigDecimal prixUnitaire;
    public static CommandeClient DtoTOCammandeClient(CommandeClientDto commandeClientDto){
        return CommandeClient.builder()
                .code(commandeClientDto.getCode())
                .etatCommand(commandeClientDto.getEtatCommand())
                .MontantsCommande(commandeClientDto.getPrixUnitaire())
                .build();
    }
    public static CommandeClientDto DtoFromCmmandeClient(CommandeClient commandeClient){
        return CommandeClientDto.builder()
                .code(commandeClient.getCode())
                .id(commandeClient.getId())
                .etatCommand(commandeClient.getEtatCommand())
                .dateCommande(commandeClient.getCreationDate())
                .mail(commandeClient.getClient().getMail())
                .prixUnitaire(commandeClient.getMontantsCommande())
                .build();
    }
    public boolean isCommandeLivree(){
        return EtatCommand.LIVREE.equals(this.etatCommand);
    }
}
