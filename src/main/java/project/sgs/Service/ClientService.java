package project.sgs.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.sgs.Dto.ClientDto;
import project.sgs.Dto.TopClient;
import project.sgs.Entity.Client;
import project.sgs.Exeption.EntityNotFoundExeption;
import project.sgs.Exeption.ErrorCode;
import project.sgs.Exeption.InvalideEntityExeption;
import project.sgs.Repository.ClientRepository;
import project.sgs.Validator.CliantValidator;

import java.util.List;
import java.util.stream.Collectors;

import static project.sgs.Dto.ClientDto.DtoToClient;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;
    public ClientDto save(ClientDto clientDto){
        List<String> errors= CliantValidator.Validotor(clientDto);
        if (!errors.isEmpty()){
            log.error("Client not valide",clientDto);
            throw new InvalideEntityExeption("L'Client n'est pas valid", ErrorCode.CLIENT_NOT_VALID,errors);
        }
        String mail=clientDto.getMail();
        if (clientRepository.findClientByMail(mail)!=null){
            throw new EntityNotFoundExeption("Changer email",ErrorCode.CLIENT_NOT_FOUND);
        }
        Client client=clientRepository.save(DtoToClient(clientDto));
        return clientDto;
    }
    public List<ClientDto> getAllClient(){
        List<Client> clients=clientRepository.findAll();
        return clients.stream().map(ClientDto::DtoFromClient).collect(Collectors.toList());
    }
    public ClientDto getClientById(Long id){
        if (id == 0){
            log.error("id note existe");
        }
        Client client=clientRepository.findClientById(id);
        return ClientDto.DtoFromClient(client);
    }
    public void deleteClient(Long id){
        if (id == 0){
            log.error("id note existe");
        }
        clientRepository.deleteById(id);
    }

    public ClientDto getClientByEmail(String mail) {
        if (mail.isEmpty()){
            log.error("mail note existe");
        }
        Client client=clientRepository.findClientByMail(mail);
        return ClientDto.DtoFromClient(client);
    }
    public List<TopClient> getbestClient() {
        return clientRepository.getTotalMontantsByClient();
}
}
