package project.sgs.Authentification;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthentificationRequest {
    private String email;
    private String password;

}
