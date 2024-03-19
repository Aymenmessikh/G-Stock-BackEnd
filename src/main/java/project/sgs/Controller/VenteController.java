package project.sgs.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.sgs.Dto.VenteDto;
import project.sgs.Service.VenteService;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/vente")
public class VenteController {
    @Autowired
    private final VenteService venteService;
    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VenteDto> save(@RequestBody VenteDto venteDto){
        VenteDto venteDto1=venteService.save(venteDto);
        return new ResponseEntity<>(venteDto1, HttpStatus.CREATED);
    }
    @GetMapping(value = "/gatAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VenteDto>> getAll(){
        List<VenteDto> venteDtos=venteService.getAll();
        return new ResponseEntity<>(venteDtos,HttpStatus.OK);
    }
    @GetMapping(value = "/getbyid/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VenteDto> getVenteByid(@PathVariable("id") Long id){
        VenteDto venteDto=venteService.findbyId(id);
        return new ResponseEntity<>(venteDto,HttpStatus.OK);
    }
    @GetMapping(value = "/getbyid/{code}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VenteDto> getCommandByCode(@PathVariable("code") String code){
        VenteDto venteDto=venteService.findByCode(code);
        return new ResponseEntity<>(venteDto,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCommandeCllient(@PathVariable Long id){
        venteService.deleteVente(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
