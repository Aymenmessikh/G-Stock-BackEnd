package project.sgs.Dto;
import lombok.Builder;
import lombok.Data;
import project.sgs.Entity.TypeMvmStock;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvmStockDto {
    private Long id;
    private ArticleDto articleDto;
    private BigDecimal quantite;
    private Instant dateMvm;

    private TypeMvmStock typeMvmStock;
}
