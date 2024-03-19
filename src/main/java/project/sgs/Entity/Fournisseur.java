package project.sgs.Entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Builder
public class Fournisseur extends AbstractEntity{
    private String nome;
    private String pronome;
    @Embedded
    private Adresse adresse;
    private String nemTel;
    private String mail;
    private String photo;
    private String photoName;
    @OneToMany(mappedBy = "fournisseur")
    private List<CommandeFournisseur> commandeFournisseurs;
}
