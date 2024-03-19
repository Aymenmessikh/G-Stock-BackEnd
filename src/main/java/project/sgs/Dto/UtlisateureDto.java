package project.sgs.Dto;
import lombok.Builder;
import lombok.Data;
import project.sgs.Entity.Roles;
import project.sgs.Entity.Utlisateure;

@Data
@Builder
public class UtlisateureDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private boolean enabled;
    private Roles role;
    private String photo;
    private String photoName;
    public static UtlisateureDto DtoFromUtilisateure(Utlisateure utlisateure){
        return UtlisateureDto.builder()
                .email(utlisateure.getEmail())
                .prenom(utlisateure.getPrenom())
                .nom(utlisateure.getNome())
                .id(utlisateure.getId())
                .password(utlisateure.getPassword())
                .photo(utlisateure.getPhoto())
                .role(utlisateure.getRole())
                .photoName(utlisateure.getPhotoName())
                .build();
    }
}
