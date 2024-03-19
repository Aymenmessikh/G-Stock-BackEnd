package project.sgs.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.sgs.Dto.FourinisseurDto;
import project.sgs.Service.FournisseureService;

import java.util.List;

@RestController
@RequestMapping("/fournisseure")
@RequiredArgsConstructor
public class FournisseureController {
    @Autowired
    private final FournisseureService fournisseureService;
    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FourinisseurDto> save(@RequestBody FourinisseurDto fourinisseurDto){
        FourinisseurDto fourinisseurDto1=fournisseureService.save(fourinisseurDto);
        return new ResponseEntity<>(fourinisseurDto1, HttpStatus.CREATED);
    }
    @GetMapping(value = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FourinisseurDto>> getAllFournisseure(){
        List<FourinisseurDto> fourinisseurDtos=fournisseureService.getAllFournisseure();
        return new ResponseEntity<>(fourinisseurDtos,HttpStatus.OK);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<FourinisseurDto> getFournisseureById(@PathVariable Long id){
        FourinisseurDto fourinisseurDto=fournisseureService.getFournisseureById(id);
        return new ResponseEntity<>(fourinisseurDto,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFournisseure(@PathVariable Long id){
        fournisseureService.deleteFournisseure(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
