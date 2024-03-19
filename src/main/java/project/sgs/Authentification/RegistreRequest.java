package project.sgs.Authentification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.sgs.Entity.Roles;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistreRequest {

        private Long id;
        private String nom;
        private String prenom;
        private String email;
        private String password;
        private String role;
        private String photo;
}
