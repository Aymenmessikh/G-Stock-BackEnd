package project.sgs.Dto;
import lombok.Builder;
import lombok.Data;
import project.sgs.Entity.CommandeFournisseur;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandefourinsseureDto {
    private Long id;
    private String code;
    private Instant dateCommande;
    private FourinisseurDto fournisseurDto;
    private List<LigneCmndFournissureDto> ligneCmndFournissureDtos;
    public static CommandeFournisseur DtoToCommandFournisseure(CommandefourinsseureDto commandefourinsseureDto){
        return CommandeFournisseur.builder()
                .fournisseur(FourinisseurDto.DtoToFourinisseure(commandefourinsseureDto.getFournisseurDto()))
                .dateCommande(commandefourinsseureDto.getDateCommande())
                .code(commandefourinsseureDto.getCode())
                .build();
    }
    public static CommandefourinsseureDto DtoFromCommandFourinsseure(CommandeFournisseur commandeFournisseur){
        return CommandefourinsseureDto.builder()
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .id(commandeFournisseur.getId())
                .fournisseurDto(FourinisseurDto.DtoFromFournisseure(commandeFournisseur.getFournisseur()))
                .build();
    }
}
