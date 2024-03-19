package project.sgs.Entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Client extends AbstractEntity{
    private String nome;
    private String pronome;
    @Embedded
    private Adresse adresse;
    private String nemTel;
    private String photoName;
    private String mail;
    private String photo;
    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;
}
