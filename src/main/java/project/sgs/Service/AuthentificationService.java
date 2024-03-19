package project.sgs.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.sgs.Authentification.AuthentificationRequest;
import project.sgs.Authentification.AuthentificationResponse;
import project.sgs.Authentification.RegistreRequest;
import project.sgs.Entity.Utlisateure;
import project.sgs.Exeption.ErrorCode;
import project.sgs.Exeption.InvalideEntityExeption;
import project.sgs.Repository.RoleRepository;
import project.sgs.Repository.UtilisateureRepository;

@Service
@RequiredArgsConstructor
public class AuthentificationService {
    private final UtilisateureRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RoleRepository rolerepository;
    private final AuthenticationManager authenticationManager;

    public AuthentificationResponse register(RegistreRequest request) {
            if (repository.findUtlisateureByEmail(request.getEmail())!=null){
                throw new InvalideEntityExeption("Utilisatuere existe dija", ErrorCode.UTILISATEURE_NOT_VALID);
            }
        var user = Utlisateure.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .Nome(request.getNom())
                .Prenom(request.getPrenom())
                .role(rolerepository.findRolesByName(request.getRole()))
                .photo(request.getPhoto())
                .enabled(true)
               // .role(request.getRole())
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthentificationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthentificationResponse authenticate(AuthentificationRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthentificationResponse.builder()
                .token(jwtToken)
                   .build();
}
}
