package project.sgs.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.sgs.Dto.CommandeClientDto;
import project.sgs.Dto.LigneCmndClientDto;
import project.sgs.Entity.EtatCommand;
import project.sgs.Entity.LigneCommandeClient;
import project.sgs.Exeption.InvalidOperationExeption;
import project.sgs.Service.CommandClientService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cmndClient")
@RequiredArgsConstructor
public class CommandeClientController {
    private final CommandClientService commandClientService;
    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto){
        CommandeClientDto commandeClientDto1=commandClientService.save(commandeClientDto);
        return new ResponseEntity<>(commandeClientDto1, HttpStatus.CREATED);
    }
    @GetMapping(value = "/gatAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommandeClientDto>> getAll(){
        List<CommandeClientDto> commandeClientDtos=commandClientService.getCommandCleintAll();
        return new ResponseEntity<>(commandeClientDtos,HttpStatus.OK);
    }
    @GetMapping(value = "/getbyid/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandeClientDto> getCommandByid(@PathVariable("id") Long id){
      CommandeClientDto commandeClientDto=commandClientService.getCommandClientById(id);
        return new ResponseEntity<>(commandeClientDto,HttpStatus.OK);
    }
    @GetMapping(value = "/getbycode/{code}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandeClientDto> getCommandByCode(@PathVariable("code") String code){
       CommandeClientDto commandeClientDto=commandClientService.getCommandClientByCode(code);
        return new ResponseEntity<>(commandeClientDto,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCommandeCllient(@PathVariable Long id){
        commandClientService.deleteCommandClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(value = "/ModifierEtatCommande/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandeClientDto> modifierEtatCmnd(@RequestBody EtatCommand etatCommand, @PathVariable("id") Long id) throws InvalidOperationExeption {
        CommandeClientDto commandeClientDto=commandClientService.modifierEtatCommande(id, etatCommand);
        return new ResponseEntity<>(commandeClientDto,HttpStatus.OK);
    }
    @PutMapping(value = "/ModifierQuantiteCommande/{id}/{LigneCmndId}/Quantite",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandeClientDto> ModiferQuantiteCmnd(@PathVariable("id") Long id,@PathVariable Long LigneCmndId,@PathVariable BigDecimal Quantite) throws InvalidOperationExeption {
        CommandeClientDto commandeClientDto=commandClientService.modfierQuantitelaCommande(Quantite,LigneCmndId,id);
        return new ResponseEntity<>(commandeClientDto,HttpStatus.OK);
    }
    @PutMapping(value = "/ModifierClientCommande/{id}/{mail}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandeClientDto> ModifierClientCmnd(@PathVariable String mail, @PathVariable("id") Long id) throws InvalidOperationExeption {
        CommandeClientDto commandeClientDto=commandClientService.modfierClientCommande(mail, id);
        return new ResponseEntity<>(commandeClientDto,HttpStatus.OK);
    }
    @PutMapping(value = "/ModifierClientCommande/{id}/{LigneCmndId}/{codeArticle}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandeClientDto> ModifierArticleCmnd(@PathVariable String codeArticle, @PathVariable("id") Long id,@PathVariable Long LigneCmndId) throws InvalidOperationExeption {
        CommandeClientDto commandeClientDto=commandClientService.ModifierArticle(id,LigneCmndId,codeArticle);
        return new ResponseEntity<>(commandeClientDto,HttpStatus.OK);
    }
    @DeleteMapping("/deleteArticle/{LigneCmndId}")
    public ResponseEntity<Void> deleteArticleCommande(@PathVariable Long LigneCmndId){
        commandClientService.DeleteArticle(LigneCmndId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/getbyCmnd/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LigneCmndClientDto>> getLigneCmndByCommand(@PathVariable("id") Long id){
        List<LigneCmndClientDto> ligneCommandeClients=commandClientService.getLigneCmndParCmnd(id);
        return new ResponseEntity<>(ligneCommandeClients,HttpStatus.OK);
    }
}
