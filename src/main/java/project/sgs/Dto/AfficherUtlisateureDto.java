package project.sgs.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.sgs.Entity.Utlisateure;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AfficherUtlisateureDto {
    private String nom;
    private String prenom;
    private String email;
    private String role;
    public static AfficherUtlisateureDto DtoFromUtilisateure(Utlisateure utlisateure){
        return AfficherUtlisateureDto.builder()
                .email(utlisateure.getEmail())
                .prenom(utlisateure.getPrenom())
                .nom(utlisateure.getNome())
                .role(utlisateure.getRole().getName())
                .build();
    }
}
