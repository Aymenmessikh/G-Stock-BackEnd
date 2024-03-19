package project.sgs.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.sgs.Entity.Adresse;
import project.sgs.Entity.Fournisseur;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FourinisseurDto {
    private Long id;
    private String nome;
    private String pronome;
    private Adresse adresse;
    private String nemTel;
    private String mail;
    private String photo;
    private String photoName;
    private List<CommandefourinsseureDto> commandeFournisseursDto;
    public static Fournisseur DtoToFourinisseure(FourinisseurDto fourinisseurDto){
        return Fournisseur.builder()
                .photo(fourinisseurDto.getPhoto())
                .adresse(fourinisseurDto.getAdresse())
                .mail(fourinisseurDto.getMail())
                .nemTel(fourinisseurDto.getNemTel())
                .nome(fourinisseurDto.getNome())
                .pronome(fourinisseurDto.getPronome())
                .photoName(fourinisseurDto.getPhotoName())
                .build();
    }
    public static FourinisseurDto DtoFromFournisseure(Fournisseur fournisseur){
        return FourinisseurDto.builder()
                .adresse(fournisseur.getAdresse())
                .pronome(fournisseur.getPronome())
                .nemTel(fournisseur.getNemTel())
                .nome(fournisseur.getNome())
                .mail(fournisseur.getMail())
                .photo(fournisseur.getPhoto())
                .id(fournisseur.getId())
                .photoName(fournisseur.getPhotoName())
                .build();
    }
}
