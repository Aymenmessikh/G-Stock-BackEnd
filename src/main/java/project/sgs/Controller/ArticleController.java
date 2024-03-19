package project.sgs.Controller;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.sgs.Dto.ArticleDto;
import project.sgs.Dto.LigneCmndClientDto;
import project.sgs.Dto.LigneCmndFournissureDto;
import project.sgs.Dto.LigneVenteDto;
import project.sgs.Service.ArticelService;
import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    @Autowired
    @Qualifier("articelService")
    private final ArticelService articelService;
    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDto> save(@RequestBody ArticleDto articleDto, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        ArticleDto articleDto1=articelService.saveArticle(articleDto,token);
        return new ResponseEntity<>(articleDto1, HttpStatus.CREATED);
    }
    @PutMapping(value = "/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDto> update(@RequestBody ArticleDto articleDto,@PathVariable("id") Long id){
        ArticleDto articleDto1=articelService.update(articleDto,id);
        return new ResponseEntity<>(articleDto1,HttpStatus.OK);
    }
    @GetMapping(value = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArticleDto>> getAll(){
        List<ArticleDto> articleDtos=articelService.getAllArticle();
        return new ResponseEntity<>(articleDtos,HttpStatus.OK);
    }
    @GetMapping(value = "/getarticlebyid/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDto> getarticleByid(@PathVariable("id") Long id){
        ArticleDto articleDto=articelService.getArticleById(id);
        return new ResponseEntity<>(articleDto,HttpStatus.OK);
    }
    @GetMapping(value = "/getarticlebycode/{codeArticle}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDto> getarticleByCode(@PathVariable("codeArticle") String codeArticle){
        ArticleDto articleDto=articelService.getArticleByCodeArticle(codeArticle);
        return new ResponseEntity<>(articleDto,HttpStatus.OK);
    }
    @GetMapping(value = "/getArticleByCategorie/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArticleDto>> getarticleByCategorie(@PathVariable("id") Long id){
        List<ArticleDto> articleDtos=articelService.getArtcileByCategorie(id);
        return new ResponseEntity<>(articleDtos,HttpStatus.OK);
    }
    @GetMapping(value = "/getArticleByCmnd/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LigneVenteDto>> getarticleByHistoriqueVentes(@PathVariable("id") Long id){
        List<LigneVenteDto> ligneVenteDtos=articelService.getHistoriqueVntes(id);
        return new ResponseEntity<>(ligneVenteDtos,HttpStatus.OK);
    }
    @GetMapping(value = "/getArticleByCmndCl/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LigneCmndClientDto>> getarticleByHistoriqueCmndClient(@PathVariable("id") Long id){
        List<LigneCmndClientDto> ligneCmndClientDtos=articelService.getHistoriqueCmndClient(id);
        return new ResponseEntity<>(ligneCmndClientDtos,HttpStatus.OK);
    }
    @GetMapping(value = "/getArticleByCmndFr/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LigneCmndFournissureDto>> getarticleByHistoriqueCmndFournisseure(@PathVariable("id") Long id){
        List<LigneCmndFournissureDto> ligneCmndFournissureDtos=articelService.getHistoriqueCmndFournisseure(id);
        return new ResponseEntity<>(ligneCmndFournissureDtos,HttpStatus.OK);
    }
    @DeleteMapping("/deletarticle/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        articelService.deletArticle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
