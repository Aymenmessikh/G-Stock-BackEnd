package project.sgs.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.sgs.Dto.LigneVenteDto;
import project.sgs.Dto.VenteDto;
import project.sgs.Entity.Article;
import project.sgs.Entity.LigneVente;
import project.sgs.Entity.Vente;
import project.sgs.Exeption.EntityNotFoundExeption;
import project.sgs.Exeption.ErrorCode;
import project.sgs.Repository.ArticleRepository;
import project.sgs.Repository.LigneVenteRepository;
import project.sgs.Repository.VenteRepository;
import project.sgs.Validator.VenteValidatore;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class VenteService {
    @Autowired
    private final VenteRepository venteRepository;
    @Autowired
    private final ArticleRepository articleRepository;
    @Autowired
    private final LigneVenteRepository ligneVenteRepository;
    public VenteDto save(VenteDto venteDto){
        List<String> errors= VenteValidatore.Validatore(venteDto);
        if (!errors.isEmpty()){
            log.error("vente not valid");
            throw new EntityNotFoundExeption("erros", ErrorCode.VENTE_NOT_VALID,errors);
        }
        venteDto.getLigneVenteDtos().forEach(ligneVenteDto -> {
            Optional<Article> article=articleRepository.findById(ligneVenteDto.getArticleDto().getId());
            if (!article.isPresent()){
                log.error("article not found");
                throw new EntityNotFoundExeption("article note existe dans la base de donne",ErrorCode.ARTICLE_NOT_FOUND);
            }
            Vente vente=venteRepository.save(VenteDto.DtoToVente(venteDto));
            venteDto.getLigneVenteDtos().forEach(ligneVenteDto1 -> {
                LigneVente ligneVente=LigneVenteDto.DtoToLigneVente(ligneVenteDto1);
                ligneVente.setVente(vente);
                ligneVenteRepository.save(ligneVente);
            });
        });
        return venteDto;
    }
    public List<VenteDto> getAll(){
        List<Vente> ventes=venteRepository.findAll();
        return ventes.stream().map(VenteDto::DtoFromVente).collect(Collectors.toList());
    }
    public VenteDto findbyId(Long id){
        if (id==null){
            log.error("aucun vente avec id="+id+"dans la bese de donnee");
        }
        Vente vente=venteRepository.findVenteById(id);
        return VenteDto.DtoFromVente(vente);
    }
    public VenteDto findByCode(String code){
        if (code==null){
            log.error("aucun vente avec id="+code+"dans la bese de donnee");
        }
        Vente vente=venteRepository.findVenteByCode(code);
        return VenteDto.DtoFromVente(vente);
    }
    public void deleteVente(Long id){
        if (id==null){
            log.error("aucun vente avec id="+id+"dans la bese de donnee");
        }
        venteRepository.deleteById(id);
    }
}
