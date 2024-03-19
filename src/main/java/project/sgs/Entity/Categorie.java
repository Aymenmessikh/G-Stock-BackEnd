package project.sgs.Entity;

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
public class Categorie extends AbstractEntity{
    private String code;
    private String designetion;
    @OneToMany(mappedBy = "categorie")
    private List<Article> articles;
}
