package project.sgs.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Builder
public class CommandeClient extends AbstractEntity{
    private String code;
    @CreatedDate
    private Instant dateCommande;
    @ManyToOne
    @JoinColumn(name = "idclient")
    private Client client;
    private BigDecimal MontantsCommande;
    private EtatCommand etatCommand;
    @OneToMany(mappedBy = "commandeClient",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<LigneCommandeClient> ligneCodeClients;
}
