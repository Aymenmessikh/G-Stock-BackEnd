package project.sgs.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.sgs.Entity.Client;
import project.sgs.Repository.ClientRepository;
@Service("clientStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveClientPhoto implements Strategy<Client>{
    @Autowired
    private final ClientRepository clientRepository;

    @Override
    public Client savePhoto(String imageName,String mail, String path) {
        Client client=clientRepository.findClientByMail(mail);
        client.setPhoto(path);
        client.setPhotoName(imageName);
        clientRepository.save(client);
        return client;
    }
}
