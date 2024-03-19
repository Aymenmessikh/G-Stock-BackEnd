package project.sgs.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.sgs.Dto.CategorieDto;
import project.sgs.Service.CategorieService;

import java.util.List;

@RestController
@RequestMapping("/categorie")
@RequiredArgsConstructor
public class CategorieController {
    @Autowired
    private final CategorieService categorieService;
    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategorieDto> save(@RequestBody CategorieDto categorieDto){
        CategorieDto categorieDto1=categorieService.save(categorieDto);
        return new ResponseEntity<>(categorieDto1, HttpStatus.CREATED);
    }
    @GetMapping(value = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategorieDto>> getAllCategorie(){
        List<CategorieDto> categorieDtos=categorieService.getAllCategorie();
        return new ResponseEntity<>(categorieDtos,HttpStatus.OK);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<CategorieDto> getCategorieById(@PathVariable Long id){
        CategorieDto categorieDto=categorieService.getCategorieById(id);
        return new ResponseEntity<>(categorieDto,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id){
        categorieService.deleteCategorie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
