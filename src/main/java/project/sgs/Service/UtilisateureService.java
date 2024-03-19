package project.sgs.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.sgs.Authentification.ChangePasswordRequest;
import project.sgs.Dto.AfficherUtlisateureDto;
import project.sgs.Dto.UtlisateureDto;
import project.sgs.Entity.Utlisateure;
import project.sgs.Repository.UtilisateureRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UtilisateureService {
    @Autowired
    private final UtilisateureRepository utilisateureRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;

    public List<UtlisateureDto> getAllUtilisateure() {
        List<Utlisateure> utlisateures = utilisateureRepository.findAll();
        return utlisateures.stream().map(UtlisateureDto::DtoFromUtilisateure).collect(Collectors.toList());
    }

    public UtlisateureDto getUtilisateureById(Long id) {
        if (id == 0) {
            log.error("id note existe");
        }
        Utlisateure utlisateure = utilisateureRepository.findUtilisateureById(id);
        return UtlisateureDto.DtoFromUtilisateure(utlisateure);
    }

    public void deleteUtilisateure(Long id) {
        if (id == 0) {
            log.error("id note existe");
        }
        utilisateureRepository.deleteById(id);
    }
    public AfficherUtlisateureDto getUtilisateureByEmail(String token) {
        if (token.isEmpty()) {
            log.error("id note existe");
        }
        String email= jwtService.extractUsername(token);
        Utlisateure utlisateure = utilisateureRepository.findUtlisateureByEmail(email);
        return AfficherUtlisateureDto.DtoFromUtilisateure(utlisateure);
    }
    public Utlisateure changePassword(ChangePasswordRequest request,String token){
        var jwt = token.substring(7);
        String email = jwtService.extractUsername(jwt);
        Utlisateure utlisateure = utilisateureRepository.findUtlisateureByEmail(email);
            if (!passwordEncoder.matches(request.getCurrentPassword(),utlisateure.getPassword())) {
                throw new IllegalStateException("Wrong Password");
        }
        utlisateure.setPassword(passwordEncoder.encode(request.getNewPassword()));
        return utilisateureRepository.save(utlisateure);
    }

}