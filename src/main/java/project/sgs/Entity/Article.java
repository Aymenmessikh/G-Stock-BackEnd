package project.sgs.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Builder
public class Article extends AbstractEntity {
    private String codeArticle;
    private String designation;
    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utlisateure utlisateure;
    private BigDecimal prixUnitairHt;
    private BigDecimal tauxtva;
    private BigDecimal prixUnitairTtc;
    private String photo;
    private String photoName;
    @ManyToOne
    @JoinColumn(name = "idcategorie")
    private Categorie categorie;
}
