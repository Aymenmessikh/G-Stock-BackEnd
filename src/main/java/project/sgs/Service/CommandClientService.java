package project.sgs.Service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.sgs.Dto.CommandeClientDto;
import project.sgs.Dto.LigneCmndClientDto;
import project.sgs.Entity.*;
import project.sgs.Exeption.EntityNotFoundExeption;
import project.sgs.Exeption.ErrorCode;
import project.sgs.Exeption.InvalidOperationExeption;
import project.sgs.Exeption.InvalideEntityExeption;
import project.sgs.Repository.ArticleRepository;
import project.sgs.Repository.ClientRepository;
import project.sgs.Repository.CommandeClientRepository;
import project.sgs.Repository.LigneCommandeClientRepository;
import project.sgs.Validator.CommandClientValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static project.sgs.Dto.CommandeClientDto.DtoFromCmmandeClient;
import static project.sgs.Dto.CommandeClientDto.DtoTOCammandeClient;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommandClientService {
    @Autowired
    private final CommandeClientRepository commandeClientRepository;
    @Autowired
    private final LigneCommandeClientRepository ligneCommandeClientRepository;
    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    private final ArticleRepository articleRepository;
    public CommandeClientDto save(CommandeClientDto commandeClientDto){
        List<String> errors=CommandClientValidator.Validatore(commandeClientDto);
        if (!errors.isEmpty()){
            log.error("command n'est pas valid ");
          throw new InvalideEntityExeption("Commad client n'est pas valid",ErrorCode.COMMANDE_CLIENT_NOT_VALID,errors);
        }
        Client client=clientRepository.findClientByMail(commandeClientDto.getMail());
        if (client==null){
            log.warn("Client is not existe");
            throw new EntityNotFoundExeption("aucun client avec ce id",ErrorCode.CLIENT_NOT_FOUND);
        }
        if (commandeClientDto==null){
            log.warn("La Command is null");
        }
        List<String> articleerror=new ArrayList<>();

        if (commandeClientDto.getLigneCmndClientDtos() !=null){
            commandeClientDto.getLigneCmndClientDtos().forEach(ligneCmndClientDto -> {
                if (ligneCmndClientDto.getCodeArticle() != null){
                    Article article=articleRepository.findArticleByCodeArticle(ligneCmndClientDto.getCodeArticle());
                    if (article==null){
                       articleerror.add("Article est vide");
                    }
                }
            });
        }
        if (!articleerror.isEmpty()){
            throw new EntityNotFoundExeption("Article not existe",ErrorCode.ARTICLE_NOT_FOUND,articleerror);
        }
        CommandeClient commandeClient=commandeClientRepository.save(DtoTOCammandeClient(commandeClientDto));
        commandeClient.setClient(client);
        if (commandeClientDto!=null) {
            commandeClientDto.getLigneCmndClientDtos().forEach(ligneCmndClientDto -> {
                LigneCommandeClient ligneCommandeClient = LigneCmndClientDto.DtoToLigneCommandClient(ligneCmndClientDto);
                ligneCommandeClient.setCommandeClient(commandeClient);
                ligneCommandeClient.setArticle(articleRepository.findArticleByCodeArticle(ligneCmndClientDto.getCodeArticle()));
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }
        return commandeClientDto;
    }
    public List<CommandeClientDto> getCommandCleintAll(){
        List<CommandeClient> commandeClients=commandeClientRepository.findAll();
        return commandeClients.stream().map(CommandeClientDto::DtoFromCmmandeClient).collect(Collectors.toList());
    }
    public CommandeClientDto getCommandClientById(Long id){
        if (id == null){
            log.error("id is null");
        }
        CommandeClient commandeClient=commandeClientRepository.findCommandeClientById(id);
        return DtoFromCmmandeClient(commandeClient);
    }
    public CommandeClientDto getCommandClientByCode(String code){
        if ( code== null){
        log.error("code is null");
    }
        CommandeClient commandeClient=commandeClientRepository.findCommandeClientByCode(code);
        return DtoFromCmmandeClient(commandeClient);

    }
    public void deleteCommandClient(Long id){
        if ( id== null){
            log.error("code is null");
        }
        commandeClientRepository.deleteById(id);
    }
    public CommandeClientDto modfierQuantitelaCommande(BigDecimal Quantite, Long LigneCmndId, Long id) throws InvalidOperationExeption {
        if (id == null && LigneCmndId ==null){
        throw new InvalideEntityExeption("La Commande Livree",ErrorCode.COMMANDE_CLIENT_NOT_FOUND);
        }
        CommandeClient commandeClient=commandeClientRepository.findCommandeClientById(id);
        if (commandeClient.getEtatCommand().equals(EtatCommand.LIVREE)){
            log.error("la commande is livree");
            throw new InvalidOperationExeption("La Commande Livree",ErrorCode.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
        if (Quantite==null || Quantite.compareTo(BigDecimal.ZERO)==0){
            log.error("Impossible de modifier le quantite par valeur null");
            throw new InvalidOperationExeption("Impossible de modifier le quantite par valeur null",ErrorCode.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
        LigneCommandeClient ligneCommandeClient=ligneCommandeClientRepository.findAllById(LigneCmndId);
        ligneCommandeClient.setQuantite(Quantite);
        ligneCommandeClientRepository.save(ligneCommandeClient);
        return DtoFromCmmandeClient(commandeClient);
    }
    public CommandeClientDto modfierClientCommande(String mail, Long id) throws InvalidOperationExeption {
        CommandeClient commandeClient=commandeClientRepository.findCommandeClientById(id);
        if (commandeClient.getEtatCommand().equals(EtatCommand.LIVREE)){
            log.error("la commande is livree");
            throw new InvalidOperationExeption("La Commande Livree",ErrorCode.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
        commandeClient.setClient(clientRepository.findClientByMail(mail));
        commandeClientRepository.save(commandeClient);
        return DtoFromCmmandeClient(commandeClient);
    }
    public CommandeClientDto modifierEtatCommande(Long id, EtatCommand etatCommand) throws InvalidOperationExeption {
        if (id ==null){
            log.error("id not existe");
            throw new InvalidOperationExeption("id not existe",ErrorCode.COMMANDE_CLIENT_NOT_FOUND);
        }
        if (StringUtils.isEmpty(String.valueOf(etatCommand))){
            log.error("etats is null");
            throw new InvalidOperationExeption("etat is null",ErrorCode.COMMANDE_CLIENT_NOT_FOUND);
        }
        CommandeClient commandeClient=commandeClientRepository.findCommandeClientById(id);
        if (commandeClient.getEtatCommand().equals(EtatCommand.LIVREE)){
            throw new InvalidOperationExeption("La Commande Livree",ErrorCode.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
        commandeClient.setEtatCommand(etatCommand);
        commandeClientRepository.save(commandeClient);
        return CommandeClientDto.DtoFromCmmandeClient(commandeClient);
    }
    public CommandeClientDto ModifierArticle(Long id,Long LigneCmndId,String codeArticle){
        if (id==0){
            log.error("id ==0");
            throw new InvalideEntityExeption("id==0",ErrorCode.COMMANDE_CLIENT_NOT_FOUND);
        }
        CommandeClient commandeClient=commandeClientRepository.findCommandeClientById(id);
        if (LigneCmndId==0){
            log.error("LigneCmndId ==0");
            throw new InvalideEntityExeption("LigneCmndId==0",ErrorCode.LIGNE_COMMANDE_CLIENT_NOT_FOUND);
        }
        LigneCommandeClient ligneCommandeClient=ligneCommandeClientRepository.findAllById(LigneCmndId);
        if (codeArticle.isEmpty()){
            log.error("code Article is empty");
            throw new InvalideEntityExeption("code Article is empty",ErrorCode.ARTICLE_NOT_FOUND);
        }
        Article article=articleRepository.findArticleByCodeArticle(codeArticle);
        ligneCommandeClient.setArticle(article);
        ligneCommandeClientRepository.save(ligneCommandeClient);
        return DtoFromCmmandeClient(commandeClient);
    }
    public void DeleteArticle(Long LigneCmndId){

        if (LigneCmndId==0){
            log.error("LigneCmndId ==0");
            throw new InvalideEntityExeption("LigneCmndId==0",ErrorCode.LIGNE_COMMANDE_CLIENT_NOT_FOUND);
        }
        LigneCommandeClient ligneCommandeClient=ligneCommandeClientRepository.findAllById(LigneCmndId);
        ligneCommandeClientRepository.deleteById(LigneCmndId);
    }
    public List<LigneCmndClientDto> getLigneCmndParCmnd(Long id){
        CommandeClient commandeClient=commandeClientRepository.findCommandeClientById(id);
        List<LigneCommandeClient> ligneCommandeClients=ligneCommandeClientRepository.findLigneCommandeClientByCommandeClient(commandeClient);
        return ligneCommandeClients.stream().map(LigneCmndClientDto::DtoFromLigneCommandClient).collect(Collectors.toList());
    }
}
