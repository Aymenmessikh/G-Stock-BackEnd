package project.sgs.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.sgs.Dto.CommandefourinsseureDto;
import project.sgs.Dto.FourinisseurDto;
import project.sgs.Entity.*;
import project.sgs.Exeption.EntityNotFoundExeption;
import project.sgs.Exeption.ErrorCode;
import project.sgs.Repository.ArticleRepository;
import project.sgs.Repository.CommandeFournisseureRepository;
import project.sgs.Repository.FourinsseurRepository;
import project.sgs.Repository.LigneCommandeFournisseureRepository;
import project.sgs.Validator.CommandFournisseureValidator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static project.sgs.Dto.CommandefourinsseureDto.DtoFromCommandFourinsseure;
import static project.sgs.Dto.CommandefourinsseureDto.DtoToCommandFournisseure;
import static project.sgs.Dto.FourinisseurDto.DtoToFourinisseure;
import static project.sgs.Dto.LigneCmndFournissureDto.DtoToLigneCommanfFournisseure;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CommandFournisseureService {
    @Autowired
    private final CommandeFournisseureRepository commandeFournisseureRepository;
    @Autowired
    private final LigneCommandeFournisseureRepository ligneCommandeFournisseureRepository;
    @Autowired
    private final ArticleRepository articleRepository;
    @Autowired
    private final FourinsseurRepository fourinsseurRepository;
    public CommandefourinsseureDto save(CommandefourinsseureDto commandefourinsseureDto){
        List<String> errors= CommandFournisseureValidator.Validatore(commandefourinsseureDto);
        if (!errors.isEmpty()){
            log.error("command fournisseure n'est pas valid");
            throw new EntityNotFoundExeption("command fournisseure n'est pas valid", ErrorCode.COMMANDE_FOURINISSEURE_NOT_VALID,errors);
        }
        FourinisseurDto fournisseurDto=commandefourinsseureDto.getFournisseurDto();
        Optional<Fournisseur> fournisseur= fourinsseurRepository.findById(DtoToFourinisseure(fournisseurDto).getId());
        if (!fournisseur.isPresent()){
            log.warn("Fournisseure not exist");
            throw new EntityNotFoundExeption("Fournisseure not exist",ErrorCode.FOURINISSEURE_NOT_FOUND);
        }
        if (commandefourinsseureDto.getLigneCmndFournissureDtos()!=null){
            commandefourinsseureDto.getLigneCmndFournissureDtos().forEach(ligneCmndFournissureDto -> {
                if (ligneCmndFournissureDto.getArticleDto()!=null){
                    Optional<Article> article=articleRepository.findById(ligneCmndFournissureDto.getArticleDto().getId());
                    if (!article.isPresent()){
                        log.error("Article not exist");
                        throw new EntityNotFoundExeption("Article Not exist",ErrorCode.ARTICLE_NOT_FOUND);
                    }
                }
            });
        }
        CommandeFournisseur commandeFournisseur=commandeFournisseureRepository
                .save(DtoToCommandFournisseure(commandefourinsseureDto));
        if (commandefourinsseureDto.getLigneCmndFournissureDtos()!=null){
           commandefourinsseureDto.getLigneCmndFournissureDtos().forEach(ligneCmndFournissureDto -> {
               LigneCommandeFournisseur ligneCommandeFournisseur=DtoToLigneCommanfFournisseure(ligneCmndFournissureDto);
               ligneCommandeFournisseur.setCommandeFournisseur(commandeFournisseur);
               ligneCommandeFournisseureRepository.save(ligneCommandeFournisseur);
           });
        }
        return commandefourinsseureDto;
    }
    public List<CommandefourinsseureDto> getCommandFournisseureAll(){
        List<CommandeFournisseur> commandeFournisseurs=commandeFournisseureRepository.findAll();
        return commandeFournisseurs.stream().map(CommandefourinsseureDto::DtoFromCommandFourinsseure).collect(Collectors.toList());
    }
    public CommandefourinsseureDto getCommandFournisseureById(Long id){
        if (id == null){
            log.error("id is null");
        }
        CommandeFournisseur commandeFournisseur=commandeFournisseureRepository.findCommandeFournisseurById(id);
        return DtoFromCommandFourinsseure(commandeFournisseur);
    }
    public CommandefourinsseureDto getCommandFournisseureByCode(String code){
        if ( code== null){
            log.error("code is null");
        }
        CommandeFournisseur commandeFournisseur=commandeFournisseureRepository.findCommandeFournisseurByCode(code);
        return DtoFromCommandFourinsseure(commandeFournisseur);

    }
    public void deleteCommandFourniiseure(Long id){
        if ( id== null){
            log.error("code is null");
        }
        commandeFournisseureRepository.deleteById(id);
    }
}
