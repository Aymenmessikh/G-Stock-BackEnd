package project.sgs.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.sgs.Dto.CommandefourinsseureDto;
import project.sgs.Service.CommandFournisseureService;

import java.util.List;
@RestController
@RequestMapping("/CommandFournisseure")
@RequiredArgsConstructor
public class CommandFournisseureController {
    private final CommandFournisseureService commandFournisseureService;
    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandefourinsseureDto> save(@RequestBody CommandefourinsseureDto commandefourinsseureDto){
        CommandefourinsseureDto commandefourinsseureDto1=commandFournisseureService.save(commandefourinsseureDto);
        return new ResponseEntity<>(commandefourinsseureDto1, HttpStatus.CREATED);
    }
    @GetMapping(value = "/gatAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommandefourinsseureDto>> getAll(){
        List<CommandefourinsseureDto> commandefourinsseureDtos=commandFournisseureService.getCommandFournisseureAll();
        return new ResponseEntity<>(commandefourinsseureDtos,HttpStatus.OK);
    }
    @GetMapping(value = "/getbyid/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandefourinsseureDto> getCommandByid(@PathVariable("id") Long id){
        CommandefourinsseureDto commandefourinsseureDto=commandFournisseureService.getCommandFournisseureById(id);
        return new ResponseEntity<>(commandefourinsseureDto,HttpStatus.OK);
    }
    @GetMapping(value = "/getbyid/{code}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandefourinsseureDto> getCommandByCode(@PathVariable("code") String code){
        CommandefourinsseureDto commandefourinsseureDto=commandFournisseureService.getCommandFournisseureByCode(code);
        return new ResponseEntity<>(commandefourinsseureDto,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCommandeCllient(@PathVariable Long id){
        commandFournisseureService.deleteCommandFourniiseure(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
