package project.sgs.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.sgs.Dto.ClientDto;
import project.sgs.Dto.TopClient;
import project.sgs.Service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    @Autowired
   private final ClientService clientService;
    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> save(@RequestBody ClientDto clientDto){
        ClientDto clientDto1=clientService.save(clientDto);
        return new ResponseEntity<>(clientDto1, HttpStatus.CREATED);
    }
    @GetMapping(value = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientDto>> getAllClient(){
        List<ClientDto> clientDtos=clientService.getAllClient();
        return new ResponseEntity<>(clientDtos,HttpStatus.OK);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id){
        ClientDto clientDto=clientService.getClientById(id);
        return new ResponseEntity<>(clientDto,HttpStatus.OK);
    }
    @GetMapping("/getByEmail/{mail}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable String mail){
        ClientDto clientDto=clientService.getClientByEmail(mail);
        return new ResponseEntity<>(clientDto,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/getAlll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TopClient>> getBestClient() {
        List<TopClient> clientDtos = clientService.getbestClient();
        return new ResponseEntity<>(clientDtos, HttpStatus.OK);
    }

}
