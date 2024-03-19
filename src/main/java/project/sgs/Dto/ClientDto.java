package project.sgs.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.sgs.Entity.Adresse;
import project.sgs.Entity.Client;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;
    private String nome;
    private String pronome;
    private Adresse adresse;
    private String nemTel;
    private String mail;
    private String photo;
    private String photoName;
    private List<CommandeClientDto> commandeClientDtos;
    public static Client DtoToClient(ClientDto clientDto){
        return Client.builder()
                .photo(clientDto.getPhoto())
                .adresse(clientDto.getAdresse())
                .mail(clientDto.getMail())
                .nemTel(clientDto.getNemTel())
                .nome(clientDto.getNome())
                .pronome(clientDto.getPronome())
                .photoName(clientDto.getPhotoName())
                .build();
    }
    public static ClientDto DtoFromClient(Client client){
        return ClientDto.builder()
                .adresse(client.getAdresse())
                .pronome(client.getPronome())
                .nemTel(client.getNemTel())
                .nome(client.getNome())
                .mail(client.getMail())
                .photo(client.getPhoto())
                .id(client.getId())
                .photoName(client.getPhotoName())
                .build();
    }
}
