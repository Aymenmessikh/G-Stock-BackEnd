package project.sgs.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Builder
public class Vente extends AbstractEntity{
    private String code;
    private Instant dateVente;
    private String commentaire;
    @OneToMany(mappedBy = "vente")
    private List<LigneVente> ligneVentes;
}
