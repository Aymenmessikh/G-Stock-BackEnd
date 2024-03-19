package project.sgs.Controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.sgs.Authentification.ChangePasswordRequest;
import project.sgs.Dto.AfficherUtlisateureDto;
import project.sgs.Dto.UtlisateureDto;
import project.sgs.Service.UtilisateureService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/utilisateure")
@RequiredArgsConstructor
public class UtlisateureController {
    @Autowired
    private final UtilisateureService utilisateureService;
    @GetMapping(value = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UtlisateureDto>> getAllUtilisateure(){
        List<UtlisateureDto> utlisateureDtos=utilisateureService.getAllUtilisateure();
        return new ResponseEntity<>(utlisateureDtos,HttpStatus.OK);
    }
    @GetMapping("/getById/{token}")
    public ResponseEntity<AfficherUtlisateureDto> getUtilisateureById(@PathVariable String token){
        AfficherUtlisateureDto utlisateureDto=utilisateureService.getUtilisateureByEmail(token);
        return new ResponseEntity<>(utlisateureDto,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        utilisateureService.deleteUtilisateure(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping ("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, HttpServletRequest reques){
        String token = reques.getHeader("Authorization");
        this.utilisateureService.changePassword(request,token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
