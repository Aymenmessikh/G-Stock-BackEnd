package project.sgs.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.sgs.Dto.ArticleDto;
import project.sgs.Dto.LigneCmndClientDto;
import project.sgs.Dto.LigneCmndFournissureDto;
import project.sgs.Dto.LigneVenteDto;
import project.sgs.Entity.Article;
import project.sgs.Entity.Categorie;
import project.sgs.Entity.Utlisateure;
import project.sgs.Exeption.ErrorCode;
import project.sgs.Exeption.InvalideEntityExeption;
import project.sgs.Repository.*;
import project.sgs.Validator.ArticleValdator;
import java.util.List;
import java.util.stream.Collectors;

import static project.sgs.Dto.ArticleDto.DtoToArticle;
import static project.sgs.Dto.ArticleDto.articleFromDto;

@Service//("ArticleService")
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ArticelService {
    @Autowired
    private final ArticleRepository articleRepository;
    private final JwtService jwtService;
    @Autowired
    private final UtilisateureRepository utilisateureRepository;
    @Autowired
    private final CategorieRepository categorieRepository;
    @Autowired
    private final LigneVenteRepository ligneventeRepository;
    @Autowired
    private final LigneCommandeClientRepository ligneCommandeClientRepository;
    @Autowired
    private final LigneCommandeFournisseureRepository ligneCommandeFournisseureRepository;
    @Autowired
    private ModelMapper modelMapper;
    private ArticleDto articletoDto(Article article) {
        ArticleDto articleDto=new ArticleDto();
        return articleDto=modelMapper.map(article,ArticleDto.class);
    }
    private Article articleFDto(ArticleDto articleDto) {
        Article article=new Article();
        return article=modelMapper.map(articleDto,Article.class);
    }
    public Categorie geybyCode(String code){
        return  categorieRepository.findCategorieByCode(code);
    }
    public ArticleDto saveArticle(ArticleDto articleDto,String token){
        List<String> errors= ArticleValdator.Validator(articleDto);
        if (!errors.isEmpty()) {
            log.error("Article not valide",articleDto);
            throw new InvalideEntityExeption("L'article n'est pas valid", ErrorCode.ARTICLE_NOT_VALIDE,errors);
        }
        final String jwt;
        jwt=token.substring(7);
        String email=jwtService.extractUsername(jwt);
        Utlisateure utlisateure=utilisateureRepository.findUtlisateureByEmail(email);


        Article article=new Article();
        article.setUtlisateure(utlisateure);
        article.setCategorie(categorieRepository.findCategorieByCode(articleDto.getCodeCategorie()));
        articleRepository.save(DtoToArticle(articleDto));
        return articleDto;
    }
    public List<ArticleDto> getAllArticle(){
        return articleRepository.findAll()
                .stream()
                .map(this::articletoDto)
                .collect(Collectors.toList());
    }
    public ArticleDto getArticleById(Long id){
        if (id == null){
            log.error("Article Id is Null");
        }
        Article article=articleRepository.findArticleById(id);
        return articletoDto(article);
    }
    public ArticleDto getArticleByCodeArticle(String codeArticle){
        if (codeArticle.isEmpty()){
            log.error("Article Id is Null");
        }
        Article article=articleRepository.findArticleByCodeArticle(codeArticle);
        return articletoDto(article);
    }
    public void deletArticle(Long id){
        if (id == null){
            log.error("Article not Existe");
        }
         articleRepository.deleteById(id);
    }
    public ArticleDto update(ArticleDto articleDto, Long id){
        List<String> errors= ArticleValdator.Validator(articleDto);
        Article article1=articleRepository.findArticleById(id);
        if (article1== null){
            log.error("aarticle not existe");
        }
        if (!errors.isEmpty()) {
            log.error("Article not valide",articleDto);
            throw new InvalideEntityExeption("L'article n'est pas valid", ErrorCode.ARTICLE_NOT_VALIDE,errors);
        }
        article1.setCodeArticle(articleDto.getCodeArticle());
        article1.setDesignation(articleDto.getDesignation());
        article1.setPhoto(articleDto.getPhoto());
        article1.setTauxtva(articleDto.getTauxtva());


        article1.setPrixUnitairHt(articleDto.getPrixUnitairHt());
        article1.setPrixUnitairTtc(articleDto.getPrixUnitairTtc());
        Article article=articleRepository.save(article1);
        article.setCategorie(geybyCode(articleDto.getCodeCategorie()));
        return articleDto;
    }
    public List<ArticleDto> getArtcileByCategorie(Long id){
        Categorie categorie=categorieRepository.findCategorieById(id);
        return articleRepository.findAllArticleByCategorie(categorie).stream()
                .map(ArticleDto::articleFromDto).collect(Collectors.toList());
    }
    public List<LigneVenteDto> getHistoriqueVntes(Long id){
        return ligneventeRepository.findAllByArticleId(id).stream().map(LigneVenteDto::DtoFromLigneVentes).collect(Collectors.toList());
    }
    public List<LigneCmndClientDto> getHistoriqueCmndClient(Long id){
        return ligneCommandeClientRepository.findAllByArticleId(id).stream().map(LigneCmndClientDto::DtoFromLigneCommandClient).collect(Collectors.toList());
    }
    public List<LigneCmndFournissureDto> getHistoriqueCmndFournisseure(Long id){
        return ligneCommandeFournisseureRepository.findAllByArticleId(id).stream()
                .map(LigneCmndFournissureDto::DtoFromLigneCommandFourniseure).collect(Collectors.toList());
    }
}